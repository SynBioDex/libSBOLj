/*
 * Copyright (c) 2012 Clark & Parsia, LLC. <http://www.clarkparsia.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sbolstandard.core.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLReader;
import org.sbolstandard.core.SBOLValidationException;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.impl.SequenceAnnotationImpl.PrecedeReference;
import org.sbolstandard.core.util.SBOLBaseVisitor;

public class SBOLReaderImpl implements SBOLReader {
	private final boolean validate;
	
	public SBOLReaderImpl(boolean validate) {
		this.validate = validate;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public SBOLDocument read(InputStream in) throws IOException, SBOLValidationException {
		try {		        
	        Unmarshaller unmarshaller = JAXB.CONTEXT.createUnmarshaller();		        
	        
	        if (validate) {       
		        unmarshaller.setSchema(JAXB.SCHEMA);
	        }
	        
	        SBOLDocument doc = (SBOLDocument) unmarshaller.unmarshal(in);
	        
	        doc.accept(new PrecedeReferenceFinder());

	        if (validate) {
	        	new SBOLValidatorImpl().validateWithoutSchema(doc);
	        }
	        
	        return doc;
        }
        catch (SBOLValidationException e) {
        	throw e;
        }
        catch (UnmarshalException e) {
        	if (e.getLinkedException() != null) {
        		throw new SBOLValidationException(e.getLinkedException());
        	}
        	else {
        		throw new SBOLValidationException(e);
        	}
        }
        catch (Exception e) {
        	throw new IOException(e);
        }
	}
	
	private class PrecedeReferenceFinder extends SBOLBaseVisitor {
		private Map<URI, SequenceAnnotation> current = null;

		@Override
	    public void visit(DnaComponent component) {
			Map<URI, SequenceAnnotation> previous = current;
			current = new HashMap<URI, SequenceAnnotation>();
			for (SequenceAnnotation annotation : component.getAnnotations()) {
				current.put(annotation.getURI(), annotation);
	        }
	        super.visit(component);
	        current = previous;
	    }

		@Override
	    public void visit(SequenceAnnotation annotation) {
			for (PrecedeReference reference : ((SequenceAnnotationImpl) annotation).getPrecedeReferences()) {
				SequenceAnnotation sa = current.get(reference.resource);
				if (sa != null) {
					reference.setValue(sa);
				}
				else if (validate) {
					throw new SBOLValidationException("Cannot resolve precede reference to sequence annotation: " + reference.resource);
				}
	        }
	        super.visit(annotation);        
	    }
	}
}
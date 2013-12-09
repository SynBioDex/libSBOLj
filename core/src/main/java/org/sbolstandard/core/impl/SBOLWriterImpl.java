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

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;

import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLValidationException;
import org.sbolstandard.core.SBOLWriter;

public class SBOLWriterImpl implements SBOLWriter {
	private final boolean validate;
	
	public SBOLWriterImpl(boolean validate) {
		this.validate = validate;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void write(SBOLDocument doc, OutputStream out) throws SBOLValidationException, IOException {
		try {
			if (validate) {
				new SBOLValidatorImpl().validateWithoutSchema(doc);
			}
	        
	        Marshaller marshaller = JAXB.CONTEXT.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        if (validate) {
		        marshaller.setSchema(JAXB.SCHEMA);
		        
		        if (!(out instanceof BufferedOutputStream)) {
		        	out = new BufferedOutputStream(out);
		        }
	        }
	        
	        marshaller.marshal(doc, out);
	        
	        out.flush();
        }	
        catch (MarshalException e) {
        	if (e.getLinkedException() != null) {
        		throw new SBOLValidationException(e.getLinkedException());
        	}
        	else {
        		throw new SBOLValidationException(e);
        	}
        }
        catch (SBOLValidationException e) {
        	throw e;
        }	
        catch (Exception e) {
        	throw new IOException(e);
        }
	}	
}
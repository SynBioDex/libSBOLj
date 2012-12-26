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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLObject;
import org.sbolstandard.core.SBOLReader;
import org.sbolstandard.core.SBOLValidationException;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.impl.SequenceAnnotationImpl.PrecedeReference;
import org.sbolstandard.core.util.SBOLBaseVisitor;
import org.sbolstandard.core.util.SBOLDeepEquality;

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
	        doc.accept(new DuplicateRemover());

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
	
	private static class DuplicateRemover extends SBOLBaseVisitor {
		private Map<URI, SBOLObject> map = new HashMap<URI, SBOLObject>();
		private Set<SBOLObject> duplicates = new HashSet<SBOLObject>();

		private void add(SBOLObject obj) {
			URI uri = obj.getURI();
			SBOLObject prev = map.get(uri);
			if (prev == null) {
				map.put(uri, obj);
			}
			else if (SBOLDeepEquality.isDeepEqual(obj, prev)) {
				duplicates.add(obj);
			}
		}
		
		@SuppressWarnings("unchecked")
        private <T extends SBOLObject> T map(T obj) {
			return duplicates.contains(obj) ? (T) map.get(obj.getURI()) : obj;
		}
	     
        private <T extends SBOLObject> void removeDuplicates(java.util.Collection<T> objects) {
        	if (objects instanceof List<?>) {
        		removeDuplicates((List) objects); 
        	}
        	
        	List<T> newObjs = new ArrayList<T>();
	        Iterator<T> i = objects.iterator();
	        while (i.hasNext()) {
	        	T obj = i.next();
	        	i.remove();
	            if (duplicates.contains(obj)) {
	            	obj = map(obj);
	            }
	            newObjs.add(obj);
            }
	        
	        for (T newObj : newObjs) {
	            objects.add(newObj);
            }
        }
	     
        private <T extends SBOLObject> void removeDuplicates(List<T> objects) {
	        ListIterator<T> i = objects.listIterator();
	        while (i.hasNext()) {
	        	T obj = i.next();
	            if (duplicates.contains(obj)) { 
	            	i.set(map(obj));
	            }
            }
        }

		@Override
        public void visit(SBOLDocument doc) {
	        super.visit(doc);
	        removeDuplicates(doc.getContents());
		}
		
		@Override
		public void visit(Collection coll) {
			super.visit(coll);
			add(coll);			
			removeDuplicates(coll.getComponents());
		}

		@Override
		public void visit(DnaComponent component) {
			super.visit(component);
			add(component);
			if (duplicates.contains(component)) {
				component.setDnaSequence(map(component.getDnaSequence()));
			}
			removeDuplicates(component.getAnnotations());
		}

		@Override
		public void visit(DnaSequence sequence) {
			super.visit(sequence);
			add(sequence);
		}

		@Override
		public void visit(SequenceAnnotation annotation) {
			super.visit(annotation);
			add(annotation);
			if (duplicates.contains(annotation.getSubComponent())) {
				annotation.setSubComponent(map(annotation.getSubComponent()));
			}
		}

	}
}
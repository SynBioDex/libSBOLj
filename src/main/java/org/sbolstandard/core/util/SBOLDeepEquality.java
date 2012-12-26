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

package org.sbolstandard.core.util;

import java.util.Iterator;

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLRootObject;
import org.sbolstandard.core.SBOLVisitable;
import org.sbolstandard.core.SequenceAnnotation;

/**
 * Class that provides functionality to check deep equality of SBOL objects.
 * 
 * @author Evren Sirin
 */
public class SBOLDeepEquality {
	/**
     * Returns <tt>true</tt> if the two specified objects are <i>deeply
     * equal</i> to one another. Unlike the {@link SBOLVisitable#equals(Object)}
     * method which checks identity equality, this method is appropriate for use with nested arrays of
     * arbitrary depth.
     *
     * <p>Two SBOLVisitable references are considered deeply equal if both
     * are <tt>null</tt>, or if they refer to objects of same type and their attributes are deeply equal. Two
     * regular object references (objects that do no implement SBOLVisitable interface) are deeply equal if they are
     * equal with respect to the standard {@link Object#equals(Object) equal} function.
	 */
	public static boolean isDeepEqual(SBOLVisitable obj1, SBOLVisitable obj2) {
		try {
	        new EqualityTester().assertEqual(obj1, obj2);
	        return true;
        }
        catch (NotEqualException e) {
	        return false;
        }
	}
	
	private static class NotEqualException extends RuntimeException {		
	}

	private static class EqualityTester extends SBOLBaseVisitor {
		private SBOLVisitable currentObj;

		public void assertEqual(SBOLVisitable obj1, SBOLVisitable obj2) {
			SBOLVisitable prevObj = currentObj;
			currentObj = obj1;
			obj2.accept(this);
			currentObj = prevObj;
		}
		
		@Override
        public void visit(SBOLDocument doc) {
            assertEqual(castCurrentAs(SBOLDocument.class), doc);
        } 				
		
		@Override
        public void visit(Collection coll) {
            assertEqual(castCurrentAs(Collection.class), coll);
        }

		@Override
        public void visit(DnaComponent component) {
			assertEqual(castCurrentAs(DnaComponent.class), component);
        }

		@Override
        public void visit(DnaSequence sequence) {
			assertEqual(castCurrentAs(DnaSequence.class), sequence);
        }

		@Override
        public void visit(SequenceAnnotation annotation) {
			assertEqual(castCurrentAs(SequenceAnnotation.class), annotation);
        }		
		
		private <T> T castCurrentAs(Class<T> cls) {
			try {
	            return cls.cast(currentObj);
            }
            catch (Exception e) {
	            throw new NotEqualException();
            }
		}
		
		private boolean isBothNullOrSame(Object obj1, Object obj2) {
			if (obj1 == obj2) {
				return true;
			}
			
			if (obj1 == null || obj2 == null) {
				throw new NotEqualException();
			}
			
			return false;			
		}
		
		private void assertEquals(Object obj1, Object obj2) {
			if (!isBothNullOrSame(obj1, obj2) && !obj1.equals(obj2)) {
				throw new NotEqualException();
			}
		}
		
		public void assertEqual(SBOLDocument doc1, SBOLDocument doc2) {
			if (isBothNullOrSame(doc1, doc2)) {
				return;
			}
			
			java.util.Collection<SBOLRootObject> components1 = doc1.getContents();
			java.util.Collection<SBOLRootObject> components2 = doc2.getContents();
			assertEquals(components1.size(), components2.size());
			Iterator<SBOLRootObject> iter1 = components1.iterator();
			Iterator<SBOLRootObject> iter2 = components2.iterator();
			while (iter1.hasNext()) {
				assertEqual(iter1.next(), iter2.next());
			}
		}

		public void assertEqual(Collection collection1, Collection collection2) {
			if (isBothNullOrSame(collection1, collection2)) {
				return;
			}
			
			assertEquals(collection1.getURI(), collection2.getURI());
			assertEquals(collection1.getDisplayId(), collection2.getDisplayId());
			assertEquals(collection1.getName(), collection2.getName());
			assertEquals(collection1.getDescription(), collection2.getDescription());

			java.util.Collection<DnaComponent> components1 = collection1.getComponents();
			java.util.Collection<DnaComponent> components2 = collection2.getComponents();
			assertEquals(components1.size(), components2.size());
			Iterator<DnaComponent> iter1 = components1.iterator();
			Iterator<DnaComponent> iter2 = components2.iterator();
			while (iter1.hasNext()) {
				assertEqual(iter1.next(), iter2.next());
			}
		}

		public void assertEqual(DnaComponent component1, DnaComponent component2) {
			if (isBothNullOrSame(component1, component2)) {
				return;
			}
			
			assertEquals(component1.getURI(), component2.getURI());
			assertEquals(component1.getDisplayId(), component2.getDisplayId());
			assertEquals(component1.getName(), component2.getName());
			assertEquals(component1.getDescription(), component2.getDescription());

			java.util.Collection<SequenceAnnotation> annotations1 = component1.getAnnotations();
			java.util.Collection<SequenceAnnotation> annotations2 = component2.getAnnotations();
			assertEquals(annotations1.size(), annotations2.size());
			Iterator<SequenceAnnotation> iter1 = annotations1.iterator();
			Iterator<SequenceAnnotation> iter2 = annotations2.iterator();
			while (iter1.hasNext()) {
				assertEqual(iter1.next(), iter2.next());
			}
		}

		public void assertEqual(DnaSequence sequence1, DnaSequence sequence2) {
			if (isBothNullOrSame(sequence1, sequence2)) {
				return;
			}
					
			assertEquals(sequence1.getURI(), sequence2.getURI());
			assertEquals(sequence1.getNucleotides(), sequence2.getNucleotides());
		}

		public void assertEqual(SequenceAnnotation annotation1, SequenceAnnotation annotation2) {
			assertEquals(annotation1.getURI(), annotation2.getURI());
			assertEquals(annotation1.getBioStart(), annotation2.getBioStart());
			assertEquals(annotation1.getBioEnd(), annotation2.getBioEnd());
			assertEquals(annotation1.getStrand(), annotation2.getStrand());
			
			assertEqual(annotation1.getSubComponent(), annotation2.getSubComponent());

			java.util.Collection<SequenceAnnotation> precedes1 = annotation1.getPrecedes();
			java.util.Collection<SequenceAnnotation> precedes2 = annotation2.getPrecedes();
			assertEquals(precedes1.size(), precedes2.size());
			Iterator<SequenceAnnotation> iter1 = precedes1.iterator();
			Iterator<SequenceAnnotation> iter2 = precedes2.iterator();
			while (iter1.hasNext()) {
				SequenceAnnotation sa1 = iter1.next();
				SequenceAnnotation sa2 = iter2.next();
				assertEquals(sa1.getURI(), sa2.getURI());
			}
		}
	}
}

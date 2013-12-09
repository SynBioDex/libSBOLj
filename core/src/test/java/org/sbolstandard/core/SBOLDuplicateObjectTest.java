// Copyright (c) 2006 - 2010, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package org.sbolstandard.core;
import static org.junit.Assert.assertTrue;
import static org.sbolstandard.core.SBOLTestUtils.*;

import org.junit.Test;
import org.sbolstandard.core.util.SBOLDeepEquality;

/**
 * 
 * @author Evren Sirin
 */
public class SBOLDuplicateObjectTest {
	@Test
	public void cyclicComponent() throws Exception {
		DnaComponent comp1 = createDnaComponent(1);
		
		SequenceAnnotation ann1 = createSequenceAnnotation(1);
		ann1.setSubComponent(comp1);
		
		comp1.addAnnotation(ann1);

		assertInvalid(createDocument(comp1), "Cyclic object reference");
	}	
	
	@Test
	public void cyclicAnnotation() throws Exception {
		DnaComponent comp1 = createDnaComponent(1);
		DnaComponent comp2 = createDnaComponent(2);
		
		SequenceAnnotation ann1 = createSequenceAnnotation(1);
		ann1.setSubComponent(comp2);
		
		comp1.addAnnotation(ann1);
		comp2.addAnnotation(ann1);

		assertInvalid(createDocument(comp1), "Cyclic object reference");
	}
	
	@Test
	public void differentComponentsWithSameURI() throws Exception {
		DnaComponent comp1a = createDnaComponent(1);
		comp1a.setName("comp1a");

		DnaComponent comp1b = createDnaComponent(1);
		comp1b.setName("comp1b");
		
		try {
            assertInvalid(createDocument(comp1a, comp1b), "Multiple objects with same URI");
        } catch (AssertionError e) {
            throw new AssertionError(String.format("Problem with DNA components\n\t%s\n\t%s", comp1a, comp1b), e);
        }
	}
	
	@Test
	public void sameComponentInMultipleCollections() throws Exception {
		DnaComponent comp1 = createDnaComponent(1);
		DnaComponent comp2 = createDnaComponent(2);
		DnaComponent comp3 = createDnaComponent(3);
		
		Collection coll1 = createCollection(1, comp1, comp2);
		Collection coll2 = createCollection(2, comp1, comp3);		

		SBOLDocument expected = createDocument(coll1, coll2);
		testDocumentWithDuplicates(expected);
	}
	
	@Test
	public void sameComponentInMultipleAnnotations() throws Exception {
		DnaComponent comp1 = createDnaComponent(1);
		DnaComponent comp2 = createDnaComponent(2);
		
		SequenceAnnotation ann1 = createSequenceAnnotation(1);
		ann1.setSubComponent(comp2);
		
		SequenceAnnotation ann2 = createSequenceAnnotation(2);
		ann2.setSubComponent(comp2);
		
		comp1.addAnnotation(ann1);
		comp1.addAnnotation(ann2);	

		SBOLDocument expected = createDocument(comp1);
		testDocumentWithDuplicates(expected);
	}
	
	private void testDocumentWithDuplicates(SBOLDocument expected) throws Exception {		
		assertValid(expected);
		
		SBOLDocument actual = writeAndRead(expected);
		
		assertTrue(SBOLDeepEquality.isDeepEqual(expected, actual));
		
		actual = writeAndRead(actual);
		
		assertTrue(SBOLDeepEquality.isDeepEqual(expected, actual));
	}
}

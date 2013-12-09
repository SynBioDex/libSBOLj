package org.sbolstandard.core;

import static org.sbolstandard.core.SBOLTestUtils.createCollection;
import static org.sbolstandard.core.SBOLTestUtils.createDnaComponent;
import static org.sbolstandard.core.SBOLTestUtils.createDnaSequence;
import static org.sbolstandard.core.SBOLTestUtils.createSequenceAnnotation;
import static org.sbolstandard.core.SBOLTestUtils.uri;

import org.junit.Test;

public abstract class SBOLAbstractTests {
	@Test
	public void valid01() throws Exception {
		DnaComponent comp1 = createDnaComponent(1);
		comp1.setName(null);

		runTest("test/data/valid01_dna_component_empty.xml", comp1);
	}	
	
	@Test
	public void valid02() throws Exception {
		DnaComponent comp1 = createDnaComponent(1);
		comp1.setDescription("DnaComponent with only name and description");

		runTest("test/data/valid02_dna_component.xml", comp1);
	}	
	
	@Test
	public void valid03() throws Exception {
		DnaComponent comp1 = createDnaComponent(1);
		comp1.setDescription("DnaComponent with sequence information");
		comp1.setDnaSequence(createDnaSequence(1));

		runTest("test/data/valid03_dna_component_sequence.xml", comp1);
	}
	
	@Test
	public void valid04() throws Exception {
		DnaComponent comp2 = createDnaComponent(2);
		comp2.setDescription("Another DNA component");

		SequenceAnnotation ann = SBOLFactory.createSequenceAnnotation();
		ann.setURI(uri("http://example.com/sa1"));
		ann.setSubComponent(comp2);

		DnaComponent comp1 = createDnaComponent(1);
		comp1.setDescription("DnaComponent with one sequence annotation");
		comp1.setDnaSequence(createDnaSequence(1));
		comp1.addAnnotation(ann);
	         
		runTest("test/data/valid04_dna_component_annotation.xml", comp1);
	}
	
	@Test
	public void valid08() throws Exception {
		DnaComponent comp1, comp2, comp3, comp4;
		SequenceAnnotation ann1, ann2, ann3;
		
		comp4 = createDnaComponent(4);
		ann3 = createSequenceAnnotation(3);
		ann3.setSubComponent(comp4);

		comp3 = createDnaComponent(3);
		ann2 = createSequenceAnnotation(2);
		ann2.addPrecede(ann3);
		ann2.setBioStart(3);
		ann2.setBioEnd(5);
		ann2.setStrand(StrandType.POSITIVE);
		ann2.setSubComponent(comp3);
		
		comp2 = createDnaComponent(2);
		comp2.setDescription("Another DNA component");
		ann1 = createSequenceAnnotation(1);
		ann1.addPrecede(ann2);
		ann1.addPrecede(ann3);
		ann1.setBioStart(1);
		ann1.setBioEnd(2);
		ann1.setSubComponent(comp2);
		  
		comp1 = createDnaComponent(1);
		comp1.setDescription("Various sequence annotations");
		comp1.setDnaSequence(createDnaSequence(1));
		comp1.addAnnotation(ann1);
		comp1.addAnnotation(ann2);
		comp1.addAnnotation(ann3);
		
		runTest("test/data/valid08_dna_component_detailed.xml", comp1);                 
	}
	
	@Test
	public void valid10() throws Exception {
		Collection coll1 = createCollection(1);
		coll1.setDescription("A collection may be empty");

		runTest("test/data/valid10_collection_empty.xml", coll1);
	}

	@Test
	public void valid11() throws Exception {
		DnaComponent comp1 = createDnaComponent(1);
		comp1.setDnaSequence(createDnaSequence(1));
		
		DnaComponent comp2 = createDnaComponent(2);
		
		Collection coll1 = createCollection(1);
		coll1.setDescription("A collection may contain multiple components");		
		coll1.addComponent(comp1);
		coll1.addComponent(comp2);

		runTest("test/data/valid11_collection_components.xml", coll1);
	}
	
	@Test
	public void test() throws Exception {
		runTest("test/data/BBa_T9002.xml", (SBOLRootObject[]) null);
	}
	
	public abstract void runTest(final String fileName, final SBOLRootObject... contents) throws Exception;
}
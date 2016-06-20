package org.sbolstandard.core2.Testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.Cut;
import org.sbolstandard.core2.OrientationType;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SequenceAnnotation;

public class OrientationTypeTestSuite {
	private SBOLDocument doc = null;
	private ComponentDefinition gRNA_b_gene = null;

	@Before
	public void setUp() throws Exception {
		String prURI="http://partsregistry.org";
		doc = new SBOLDocument();
		doc.setDefaultURIprefix(prURI);
		doc.setTypesInURIs(false);
		doc.setComplete(true);
		
		gRNA_b_gene = doc.createComponentDefinition("gRNA_b_gene", ComponentDefinition.DNA);

	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void test_toString() throws SBOLValidationException
	{
		SequenceAnnotation gen_cut = gRNA_b_gene.createSequenceAnnotation("cutAt6", "cut2", OrientationType.INLINE);		
		assertNotNull(gRNA_b_gene.getSequenceAnnotation("cutAt6"));
		System.out.println(gen_cut.getLocation("cutAt6"));
		
		assertTrue(gen_cut.getLocation("cut2").toString().contains(OrientationType.INLINE.toString()));
		//Cut cut = (Cut) gen_cut.getLocation("cutAt2");
		//assertTrue(cut.toString().contains(cut.getIdentity().toString()));
	}
	
}

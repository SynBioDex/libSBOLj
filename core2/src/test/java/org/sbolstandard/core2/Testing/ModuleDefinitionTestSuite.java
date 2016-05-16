package org.sbolstandard.core2.Testing;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.Component;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.DirectionType;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;

public class ModuleDefinitionTestSuite {
	/*private SBOLDocument doc = null;
	private ComponentDefinition gRNA_b_gene = null;
	private Component promoter = null;
	private Component terminator = null;
	private Component gene_seq = null;
	private ComponentDefinition target_protein = null;
	private ModuleDefinition template = null;
	@Before
	public void setUp() throws Exception {
		doc = new SBOLDocument();
		doc.setDefaultURIprefix("http://sbols.org/CRISPR_Example/");
		doc.setComplete(true);
		doc.setCreateDefaults(true);
		
		gRNA_b_gene = doc.createComponentDefinition("gRNA_b_gene", ComponentDefinition.DNA);
		System.out.println(gRNA_b_gene.getIdentity());
		promoter = gRNA_b_gene.createComponent("promoter", AccessType.PRIVATE, "promoter");
		terminator = gRNA_b_gene.createComponent("terminator", AccessType.PRIVATE, "terminator");
		gene_seq = gRNA_b_gene.createComponent("gene_seq", AccessType.PRIVATE, "gene_seq");
		
		target_protein = doc.createComponentDefinition("target_protein", ComponentDefinition.DNA);
		template = doc.createModuleDefinition("template");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_createFuncComp() throws SBOLValidationException
	{
		template.createFunctionalComponent("template_circuit", AccessType.PUBLIC, "template_fc", DirectionType.NONE);
		assertNotNull(template.getFunctionalComponent("template_circuit"));
		
	}
	*/
}

package org.sbolstandard.core2.Testing;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.Component;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.RefinementType;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SequenceOntology;

public class ComponentTest {
	private SBOLDocument doc = null;
	private ComponentDefinition gRNA_b_gene = null;
	private ComponentDefinition target_gene_CD = null;

	private ComponentDefinition target_gene_comp_CD = null;
	private ComponentDefinition promoter_CD = null;
	private ComponentDefinition gene_CD = null;
	private ComponentDefinition terminator_CD = null;

	private Component promoter = null;
	private Component gene = null;
	private Component target_gene = null;
	private Component terminator = null;
	
	@Before
	public void setUp() throws Exception {
		String prURI="http://partsregistry.org";
		doc = new SBOLDocument();
		doc.setDefaultURIprefix(prURI);
		doc.setTypesInURIs(false);
		doc.setComplete(true);
		/*create CD's for main CD and sub-components*/
		gRNA_b_gene = doc.createComponentDefinition("gRNA_b_gene", "", ComponentDefinition.DNA);
		target_gene_CD = doc.createComponentDefinition("target_gene_CD", "", ComponentDefinition.DNA);
		target_gene_comp_CD = doc.createComponentDefinition("target_gene_comp_CD", "", ComponentDefinition.DNA);
		target_gene = target_gene_CD.createComponent("target_gene", AccessType.PUBLIC, "target_gene_comp_CD");
		promoter_CD = doc.createComponentDefinition("promoter_CD", "", ComponentDefinition.DNA);
		gene_CD = doc.createComponentDefinition("gene_CD", "", ComponentDefinition.DNA);
		terminator_CD = doc.createComponentDefinition("terminator_CD", "", ComponentDefinition.DNA);
		/*create Components   */
		promoter = gRNA_b_gene.createComponent("promoter", AccessType.PUBLIC, "promoter_CD");
		gene = gRNA_b_gene.createComponent("gene", AccessType.PUBLIC, "promoter_CD");
		terminator = gRNA_b_gene.createComponent("terminator", AccessType.PUBLIC, "promoter_CD");
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void test_roleMethods()
	{	
		/*add roles    */
		assertTrue(promoter.addRole(SequenceOntology.PROMOTER));
		assertTrue(promoter.getRoles().contains(SequenceOntology.PROMOTER));
		assertTrue(promoter.containsRole(SequenceOntology.PROMOTER));
		/*remove role*/
		promoter.removeRole(SequenceOntology.PROMOTER);
		assertFalse(promoter.containsRole(SequenceOntology.PROMOTER));
		/*clear Roles  */
		assertTrue(promoter.addRole(SequenceOntology.PROMOTER));
		promoter.clearRoles();
		assertFalse(promoter.containsRole(SequenceOntology.PROMOTER));
		
		Set<URI> promoter_roles = new HashSet<URI>();
		promoter_roles.add(SequenceOntology.PROMOTER);
		promoter.setRoles(promoter_roles);
		assertTrue(promoter.containsRole(SequenceOntology.PROMOTER));
	}
	
	/*@Test
	public void test_MapsTo() throws SBOLValidationException
	{
		target_gene.createMapsTo("local_gene", RefinementType.USELOCAL, gene.getDisplayId(), target_gene.getDisplayId());
	
	} */
	
}

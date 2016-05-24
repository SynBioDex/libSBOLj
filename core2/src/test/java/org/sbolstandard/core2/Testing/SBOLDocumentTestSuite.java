package org.sbolstandard.core2.Testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.Model;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SystemsBiologyOntology;
import org.sbolstandard.core2.TopLevel;
import org.sbolstandard.core2.ValidationTest;

public class SBOLDocumentTestSuite {
	private SBOLDocument doc = null;
	private ComponentDefinition gRNA_promoter = null;
	private ComponentDefinition CRa_promoter = null;
	private ComponentDefinition TetR_promoter = null;
	private TopLevel gRNA_b_gene = null;
	private String prURI="http://partsregistry.org";

	@Before
	public void setUp() throws Exception {
		doc = new SBOLDocument();
		doc.setDefaultURIprefix(prURI);
		doc.setTypesInURIs(false);
		doc.setComplete(true);
		
		 gRNA_promoter = doc.createComponentDefinition("http://partsregistry.org", "gRNA_promoter", "", ComponentDefinition.DNA);
		 CRa_promoter = doc.createComponentDefinition(prURI, "CRa_promoter", "", ComponentDefinition.DNA);
		 TetR_promoter = doc.createComponentDefinition(prURI, "TetR_promoter","", ComponentDefinition.DNA); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_docModelMethods() throws SBOLValidationException {
		Model model=doc.createModel(
				"pIKE_Toggle_1",
				"1.0",
				URI.create("http://virtualparts.org/part/pIKE_Toggle_1"),
				URI.create("http://identifiers.org/edam/format_2585"), 
				SystemsBiologyOntology.CONTINUOUS_FRAMEWORK); 
		assertTrue(doc.getModels().size() == 1);
		assertTrue(doc.getModel("pIKE_Toggle_1", "").equals(model));
		doc.clearModels();
		assertTrue(doc.getModels().size() == 0);		
	}
	
	@Test
	public void test_createTLmethods() throws SBOLValidationException
	{
		ComponentDefinition gRNA_b_gene = doc.createComponentDefinition("http://partsregistry.org", "gRNA_b_gene", "", ComponentDefinition.DNA);
		assertNotNull(gRNA_b_gene);
		assertTrue(doc.getComponentDefinition("gRNA_b_gene", "").equals(gRNA_b_gene));
	}
	
	@Test
	public void test_namespaceMethods() throws URISyntaxException
	{
		List<QName> doc_namespaces = doc.getNamespaces();
		QName namespace = doc_namespaces.get(0);
		assertTrue(doc.getNamespaces().size() == 4);
		QName created_ns = new QName("http://www.w3.org/1999/02/prov#");
		doc.addNamespace(created_ns);
		assertTrue(doc.getNamespaces().size() == 5);
		doc.removeNamespace(new URI(created_ns.getNamespaceURI()));
		assertTrue(doc.getNamespace(new URI(created_ns.getNamespaceURI())).toString().length() == 0);
		doc.addNamespace(created_ns);
		assertTrue(doc.getNamespaces().size() == 5);

		//what if namespace is empty
		//doc.clearNamespaces();

	}
	
	@Test
	public void test_renameTopLevel() throws SBOLValidationException
	{		 
		gRNA_b_gene = doc.createComponentDefinition("http://partsregistry.org", "gRNA_b_gene", "", ComponentDefinition.DNA);
		doc.rename(gRNA_b_gene, "gRNA_b_gene2");
		assertNotNull(doc.getComponentDefinition("gRNA_b_gene2", ""));
		assertNull(doc.getComponentDefinition("gRNA_b_gene", ""));
		doc.rename(gRNA_b_gene, "gRNA_b_gene3", "");
		assertNotNull(doc.getComponentDefinition("gRNA_b_gene3", ""));
		doc.rename(gRNA_b_gene, prURI, "gRNA_b_gene4", "");
		assertNotNull(doc.getComponentDefinition("gRNA_b_gene4", ""));
	}
	
	@Test
	public void test_readFile() throws SBOLValidationException, URISyntaxException, IOException, SBOLConversionException
	{
		/*SBOLDocument test_doc = new SBOLDocument();
		test_doc.setDefaultURIprefix(prURI);
		test_doc.setTypesInURIs(false);
		test_doc.setComplete(true);
		File file_base = new File(SBOLDocumentTestSuite.class.getResource("/test/data/toggle.rdf/").toURI());
		test_doc.read(file_base);
		assertNotNull(test_doc);
		test_doc = new SBOLDocument();
		test_doc.setDefaultURIprefix(prURI);
		test_doc.setTypesInURIs(false);
		test_doc.setComplete(true);
		test_doc.read(file_base.toString());
		assertNotNull(test_doc);
		test_doc = new SBOLDocument();
		test_doc.setDefaultURIprefix(prURI);
		test_doc.setTypesInURIs(false);
		test_doc.setComplete(true);
		InputStream file = new FileInputStream(file_base.toString());
		test_doc.read(file);
		assertNotNull(test_doc); */
	}		

	

}

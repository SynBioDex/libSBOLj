package org.sbolstandard.core2.Testing;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sbolstandard.core2.Collection;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.GenericTopLevel;
import org.sbolstandard.core2.Model;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLFactory;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.Sequence;

public class SBOLFactoryTestSuite {
	private String prURI="http://partsregistry.org";
	private SBOLDocument repression_doc = null;
	private SBOLDocument actual_doc = null;



	@Before
	public void setUp() throws Exception {
		actual_doc = new SBOLDocument();
		repression_doc = new SBOLDocument();
		actual_doc.setDefaultURIprefix(prURI);
		actual_doc.setTypesInURIs(false);
		actual_doc.setComplete(true);
		InputStream docAsStream = SequenceConstraintTest.class.getResourceAsStream("/SBOLTestSuite/SBOL2/RepressionModel.xml");
		actual_doc.read(docAsStream);
				
		SBOLFactory.setSBOLDocument(repression_doc);
		SBOLFactory.setDefaultURIprefix(prURI);
		SBOLFactory.setTypesInURIs(false);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_addTopLevels() throws URISyntaxException, SBOLValidationException {
		SBOLFactory.setComplete(true);
		assertTrue(SBOLFactory.isComplete());
		assertFalse(SBOLFactory.isTypesInURIs());
		assertFalse(SBOLFactory.isCreateDefaults());
		for(ComponentDefinition cd: actual_doc.getComponentDefinitions())
				SBOLFactory.createComponentDefinition(cd.getDisplayId(), cd.getIdentity());
		assertTrue(actual_doc.getComponentDefinitions().size() == repression_doc.getComponentDefinitions().size());
		SBOLFactory.clearComponentDefinitions();
		assertTrue(SBOLFactory.getComponentDefinitions().size() == 0);
		for(ComponentDefinition cd: actual_doc.getComponentDefinitions())
			SBOLFactory.createComponentDefinition(cd.getDisplayId(), cd.getVersion(), cd.getTypes());
		assertTrue(actual_doc.getComponentDefinitions().size() == repression_doc.getComponentDefinitions().size());
		for(ModuleDefinition md : actual_doc.getModuleDefinitions())
				SBOLFactory.createModuleDefinition(md.getDisplayId());
		assertTrue(actual_doc.getModuleDefinitions().size() == repression_doc.getModuleDefinitions().size());
		SBOLFactory.clearModuleDefinitions();
		assertTrue(SBOLFactory.getModuleDefinitions().size() == 0);
		for(Sequence s : actual_doc.getSequences())
				SBOLFactory.createSequence(s.getDisplayId(), s.getElements(), s.getEncoding());
		assertTrue(actual_doc.getSequences().size() == repression_doc.getSequences().size());
		SBOLFactory.clearSequences();
		assertTrue(SBOLFactory.getSequences().size() == 0);
		for(Model m : actual_doc.getModels())
			SBOLFactory.createModel(m.getDisplayId(), m.getSource(), m.getLanguage(), m.getFramework());
		assertTrue(actual_doc.getModels().size() == repression_doc.getModels().size());
		SBOLFactory.clearModels();
		assertTrue(SBOLFactory.getModels().size() == 0);
		for(Collection c : actual_doc.getCollections())
			SBOLFactory.createCollection(c.getDisplayId());
		assertTrue(actual_doc.getCollections().size() == repression_doc.getCollections().size());
		SBOLFactory.clearCollections();
		assertTrue(SBOLFactory.getCollections().size() == 0);
	}
	
	@Test
	public void test_createCD() throws SBOLValidationException
	{		
		SBOLFactory.setComplete(true);
		ComponentDefinition temp = null;
		for(ComponentDefinition cd: actual_doc.getComponentDefinitions()){
			temp = SBOLFactory.createComponentDefinition(cd.getDisplayId(), cd.getTypes());
		}
		assertNotNull(SBOLFactory.getComponentDefinition(temp.getDisplayId(), temp.getVersion()));
		assertNotNull(SBOLFactory.getComponentDefinition(temp.getIdentity()));
		assertTrue(actual_doc.getComponentDefinitions().size() == repression_doc.getComponentDefinitions().size());
		SBOLFactory.removeComponentDefinition(temp);
		assertNull(SBOLFactory.getComponentDefinition(temp.getDisplayId(), temp.getVersion()));
		assertNull(SBOLFactory.getComponentDefinition(temp.getIdentity()));
		SBOLFactory.clearComponentDefinitions();
		assertTrue(SBOLFactory.getComponentDefinitions().size() == 0);
	
		for(ComponentDefinition cd: actual_doc.getComponentDefinitions())
			SBOLFactory.createComponentDefinition(cd.getDisplayId(), cd.getIdentity());
		assertTrue(actual_doc.getComponentDefinitions().size() == repression_doc.getComponentDefinitions().size());
		SBOLFactory.clearComponentDefinitions();
		assertTrue(SBOLFactory.getComponentDefinitions().size() == 0);
	
		for(ComponentDefinition cd: actual_doc.getComponentDefinitions())
			SBOLFactory.createComponentDefinition(cd.getDisplayId(), cd.getVersion(), cd.getTypes());
		assertTrue(actual_doc.getComponentDefinitions().size() == repression_doc.getComponentDefinitions().size());
		SBOLFactory.clearComponentDefinitions();
		assertTrue(SBOLFactory.getComponentDefinitions().size() == 0);

		for(ComponentDefinition cd: actual_doc.getComponentDefinitions())
			SBOLFactory.createComponentDefinition(cd.getDisplayId(), cd.getVersion(), cd.getIdentity());
		assertTrue(actual_doc.getComponentDefinitions().size() == repression_doc.getComponentDefinitions().size());
		SBOLFactory.clearComponentDefinitions();
		assertTrue(SBOLFactory.getComponentDefinitions().size() == 0);

		for(ComponentDefinition cd: actual_doc.getComponentDefinitions())
			SBOLFactory.createComponentDefinition(prURI, cd.getDisplayId(), cd.getVersion(), cd.getIdentity());
		assertTrue(actual_doc.getComponentDefinitions().size() == repression_doc.getComponentDefinitions().size());
		SBOLFactory.clearComponentDefinitions();
		assertTrue(SBOLFactory.getComponentDefinitions().size() == 0);

		for(ComponentDefinition cd: actual_doc.getComponentDefinitions())
			SBOLFactory.createComponentDefinition(prURI, cd.getDisplayId(), cd.getVersion(), cd.getTypes());
		assertTrue(actual_doc.getComponentDefinitions().size() == repression_doc.getComponentDefinitions().size());
		SBOLFactory.clearComponentDefinitions();
		assertTrue(SBOLFactory.getComponentDefinitions().size() == 0);
	}
	
	@Test
	public void test_createModuleDefinitions() throws SBOLValidationException
	{
		SBOLFactory.setComplete(true);
		ModuleDefinition temp = null;
		for(ModuleDefinition md : actual_doc.getModuleDefinitions())
			temp = SBOLFactory.createModuleDefinition(md.getDisplayId(), md.getVersion());
	
		assertNotNull(SBOLFactory.getModuleDefinition(temp.getDisplayId(), temp.getVersion()));
		assertNotNull(SBOLFactory.getModuleDefinition(temp.getIdentity()));
		assertTrue(actual_doc.getModuleDefinitions().size() == repression_doc.getModuleDefinitions().size());
		SBOLFactory.clearModuleDefinitions();
		assertTrue(SBOLFactory.getModuleDefinitions().size() == 0);
		SBOLFactory.removeModuleDefinition(temp);
		assertNull(SBOLFactory.getModuleDefinition(temp.getIdentity()));
		assertNull(SBOLFactory.getModuleDefinition(temp.getDisplayId(), temp.getVersion()));
		for(ModuleDefinition md : actual_doc.getModuleDefinitions())
			SBOLFactory.createModuleDefinition(prURI,md.getDisplayId(), md.getVersion());
		assertTrue(actual_doc.getModuleDefinitions().size() == repression_doc.getModuleDefinitions().size());
		SBOLFactory.clearModuleDefinitions();
		assertTrue(SBOLFactory.getModuleDefinitions().size() == 0);
	}
	
	@Test
	public void test_creatSequences() throws SBOLValidationException
	{
		SBOLFactory.setComplete(true);
		Sequence temp = null;
		for(Sequence s : actual_doc.getSequences()){
			temp = SBOLFactory.createSequence(prURI, s.getDisplayId(),s.getVersion(), s.getElements(), s.getEncoding());
		}
		assertNotNull(SBOLFactory.getSequence(temp.getDisplayId(), temp.getVersion()));
		assertNotNull(SBOLFactory.getSequence(temp.getIdentity()));
		assertTrue(actual_doc.getSequences().size() == repression_doc.getSequences().size());
		SBOLFactory.clearSequences();
		assertTrue(SBOLFactory.getSequences().size() == 0);
		SBOLFactory.removeSequence(temp);
		assertNull(SBOLFactory.getSequence(temp.getIdentity()));
		assertNull(SBOLFactory.getSequence(temp.getDisplayId(), temp.getVersion()));
		for(Sequence s : actual_doc.getSequences())
			SBOLFactory.createSequence(s.getDisplayId(), s.getVersion(), s.getElements(), s.getEncoding());
		assertTrue(actual_doc.getSequences().size() == repression_doc.getSequences().size());
		SBOLFactory.clearSequences();
		assertTrue(SBOLFactory.getSequences().size() == 0);
	}
	
	@Test
	public void test_copy() throws SBOLValidationException
	{
		SBOLFactory.createCopy(actual_doc);
		assertTrue(repression_doc.equals(actual_doc));
	}
	
	

}

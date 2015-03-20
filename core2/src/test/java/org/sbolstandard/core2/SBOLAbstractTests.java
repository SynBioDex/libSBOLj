package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.junit.Test;
import org.sbolstandard.core2.FunctionalComponent.DirectionType;
import org.sbolstandard.core2.abstract_classes.ComponentInstance.AccessType;

public abstract class SBOLAbstractTests {

	//TODO: singleCollection_Members
	//TODO: multipleCollections
	//TODO: multipleGenericTopLevels

	/*TODO: ComponentDefinition
	 * formatSubComponents(c.getSubComponents(),list);
	 * formatSequenceAnnotations(c.getSequenceAnnotations(),list);
	 * formatSequenceConstraints(c.getSequenceConstraints(),list);
	 */

	@Test
	public void test_singleCollection_no_Members() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		List<Annotation> annotations = new ArrayList<Annotation>();
		Annotation a = new Annotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
				new Turtle("turtleString"));
		annotations.add(a);

		SBOLTestUtils.createCollection(document, "myParts", null);

		runTest("test/data/singleCollection.rdf", document);
	}

	@Test
	public void test_singleGenericTopLevel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel");
		runTest("test/data/singleGenericTopLevel.rdf", document);
	}

	@Test
	public void test_singleModel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createModel(document, "ToggleModel");
		runTest("test/data/singleModel.rdf", document);
	}


	@Test
	public void test_singleSequence() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createSequence(document,"pLacSeq");

		runTest("test/data/singleSequence.rdf", document);
	}

	@Test
	public void test_multipleSquences() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createSequence(document,"pLacSeq");
		SBOLTestUtils.createSequence(document,"tetRSeq");
		SBOLTestUtils.createSequence(document,"pLactetRSeq");
		runTest("test/data/multipleSequences.rdf", document);
	}

	@Test
	public void test_single_emptyModuleDefinition() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		Set<URI> roles = SBOLTestUtils.getSetPropertyURI("Inverter");
		//		List<FunctionalComponent> functionalComponent_data;
		//		List<Interaction> interactionData;
		//		List<Module> submodule_data;
		//		Set<URI> model_data;
		//		List<Annotation> annotations;
		SBOLTestUtils.createModuleDefinition(document, "LacI_Inv",
				roles, null, null, null, null, null);

		runTest("test/data/singleModuleDefinition.rdf", document);
	}

	//TODO: CURRENTLY DEBUGGING
	//	@Test
	//	public void test_ToggleSwitch() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
	//		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
	//
	//		Set<URI> roles = SBOLTestUtils.getSetPropertyURI("Inverter");
	//
	//		AccessType accessType 		= AccessType.PUBLIC;
	//		DirectionType directionType = DirectionType.INPUT;
	//
	//		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
	//		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");
	//
	//		ComponentDefinition cd1 = SBOLTestUtils.createComponentDefinition(document, "TetR",
	//				type, role, null,
	//				null, null, null);
	//
	//		FunctionalComponent fc1 =
	//				SBOLTestUtils.createFunctionalComponent("LacISp",
	//						accessType, directionType,
	//						cd1.getIdentity());
	//
	//		ComponentDefinition cd2 = SBOLTestUtils.createComponentDefinition(document, "TetR",
	//				type, role, null,
	//				null, null, null);
	//
	//		FunctionalComponent fc2 =
	//				SBOLTestUtils.createFunctionalComponent("TetRSp",
	//						accessType, directionType,
	//						cd2.getIdentity());
	//
	//		List<FunctionalComponent> functionalComponents = new ArrayList<FunctionalComponent>();
	//		functionalComponents.add(fc1);
	//		functionalComponents.add(fc2);
	//
	//		//----END OF functionalComponents -----
	//
	//		/*
	//		 * createMapTo(getURI("Toggle/Inv1/Inv1a/1/0"), RefinementType.USELOCAL, get_LacISp(SBOL2Doc_test), get_LacIIn(SBOL2Doc_test)),
	//		 * createMapTo(getURI("Toggle/Inv1/Inv2a_TetRSp/1/0"), RefinementType.USELOCAL, get_TetRSp(SBOL2Doc_test), get_TetROut(SBOL2Doc_test))
	//
	//		 */
	//
	//		List<Annotation> annotations1 = new ArrayList<Annotation>();
	//		Annotation a1 = new Annotation(
	//				new QName("http://myannotation.org", "thisAnnotation", "annot"),
	//				new Turtle("turtleString"));
	//		annotations1.add(a1);
	//
	//		FunctionalComponent fc = SBOLTestUtils.createFunctionalComponent("LacInv",
	//				AccessType.PRIVATE, directionType.NONE, instantiatedComponent);
	//
	//		List<Participation> part1 = new ArrayList<Participation>();
	//
	//		Set<URI> part1_roles = SBOLTestUtils.getSetPropertyURI("Inverter");
	//		Participation p1 = new Participation("p1a", part1_roles, participant);
	//
	//		Set<URI> part2_roles = SBOLTestUtils.getSetPropertyURI("repressed");
	//		Participation p2 = new Participation("p2a", part2_roles, participant);
	//
	//		part1.add(p1);
	//		part1.add(p2);
	//
	//		List<Interaction> interact1 = new ArrayList<Interaction>();
	//		Set<URI> interaction_type = SBOLTestUtils.getSetPropertyURI("repression");
	//		Interaction i1 = new Interaction("interact1", interaction_type,
	//				part1); //line 397
	//		Interaction i2 = new Interaction("interact2", interaction_type,
	//				participations); //line 397
	//		interact1.add(i1);
	//		interact1.add(i2);
	//
	//		ModuleDefinition md1 = SBOLTestUtils.createModuleDefinition(document, "LacI_Inv",
	//				roles,
	//				functionalComponent_data,
	//				interactionData,
	//				null,
	//				null,
	//				annotations1); //line 413
	//
	//		MapsTo m1 = new MapsTo("Inv1a", RefinementType.USELOCAL, local, remote);
	//		MapsTo m2 = new MapsTo("Inv1a", RefinementType.USELOCAL, local, remote);
	//		List<MapsTo> maps1 = new ArrayList<MapsTo>();
	//
	//		Module mod1 = SBOLTestUtils.createModuleData(document, "Inv1",
	//				md1.getIdentity(), maps1); //line 621
	//
	//		List<Module> subModules = new ArrayList<Module>();
	//		subModules.add(mod1);
	//		//		Set<URI> model_data;
	//		//		List<Annotation> annotations;
	//
	//		SBOLTestUtils.createModuleDefinition(document, "LacI_Inv",
	//				roles, functionalComponents, null, subModules, null, null);
	//
	//		runTest("test/data/singleModuleDefinition.rdf", document);
	//	}


	@Test
	public void test_singleComponentDefinition() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		SBOLTestUtils.createComponentDefinition(document, "pLac", type, role,
				null, null, null, null);

		runTest("test/data/singleComponentDefinition.rdf", document);
	}

	@Test
	public void test_singleCompDef_withSeq() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		URI s = SBOLTestUtils.createSequence(document, "pLacSeq").getIdentity();
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		SBOLTestUtils.createComponentDefinition(document, "pLac", type, role,
				s, null, null, null);

		runTest("test/data/singleCompDef_withSeq.rdf", document);
	}

	@Test
	public void test_singleFunctionalComponent() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");
		URI compdef = SBOLTestUtils.createComponentDefinition(document, "LacIIn", type, role,
				null, null, null, null).getIdentity();

		AccessType accessType 		= AccessType.PUBLIC;
		DirectionType directionType = DirectionType.INPUT;
		SBOLTestUtils.createFunctionalComponent("LacIIn", accessType, directionType, compdef);

		runTest("test/data/singleFunctionalComponent.rdf", document);
	}

	public abstract void runTest(final String fileName, final SBOLDocument expected)
			throws Exception;

}
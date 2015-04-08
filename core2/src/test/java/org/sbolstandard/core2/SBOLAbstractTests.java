package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.junit.Test;
import org.sbolstandard.core2.FunctionalComponent.DirectionType;
import org.sbolstandard.core2.MapsTo.RefinementType;
import org.sbolstandard.core2.abstract_classes.ComponentInstance;
import org.sbolstandard.core2.abstract_classes.ComponentInstance.AccessType;

/**
 * Test the validity of the code serialization and not the conceptual bio models.
 * @author Tramy Nguyen
 *
 */
public abstract class SBOLAbstractTests {

	//TODO: create a test where you are passing in an object that does not match up with the file
	@Test
	public void test_moduleDef_withFunctionalComponent() throws Exception
	{
		SBOLDocument document=new SBOLDocument();

		Set<URI> roles = SBOLTestUtils.getSetPropertyURI("Inverter");
		ModuleDefinition moduleDefinition=document.createModuleDefinition(URI.create("testmodule"),roles);
		FunctionalComponent fc=moduleDefinition.createComponent(
				URI.create("fc1"),
				ComponentInstance.AccessType.PUBLIC,
				URI.create("cd1"),
				FunctionalComponent.DirectionType.NONE);
		SBOLWriter.writeRdf(document,(System.out));
	}

	//	TODO: singleCollection_Members
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
	public void test_multipleCollections_no_Members() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		SBOLTestUtils.createCollection(document, "myPart1", null);
		SBOLTestUtils.createCollection(document, "myPart2", null);
		SBOLTestUtils.createCollection(document, "myPart3", null);

		runTest("test/data/multipleCollections_no_Members.rdf", document);
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
	public void test_multipleGenericTopLevel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel1");
		SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel2");
		SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel3");
		runTest("test/data/multipleGenericTopLevel.rdf", document);
	}

	@Test
	public void test_singleModel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createModel(document, "ToggleModel", null);
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

	//	|------------------------------------TOGGLE SWITCH------------------------------------|
	//	|		- double check on correct use of URIs										  |
	//	|																					  |
	//	|-------------------------------------------------------------------------------------|
	/*@Test
	public void test_ToggleSwitch() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		List<Annotation> annotations = new ArrayList<Annotation>();
		Annotation a = new Annotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
				new Turtle("turtleString"));
		annotations.add(a);

		//Sequence
		URI pLacSeq_id = SBOLTestUtils.createSequence(document,"pLacSeq").getIdentity();
		URI tetRSeq_id = SBOLTestUtils.createSequence(document,"tetRSeq").getIdentity();
		URI pLactetRSeq_id = SBOLTestUtils.createSequence(document,"pLactetRSeq").getIdentity();

		URI ptetSeq_id = SBOLTestUtils.createSequence(document,"ptetSeq").getIdentity();
		URI lacISeq_id = SBOLTestUtils.createSequence(document,"lacISeq").getIdentity();
		URI ptetlacISeq_id = SBOLTestUtils.createSequence(document,"ptetlacISeq").getIdentity();

		//ComponentDefintion
		URI pLac_id = get_pLac(document, pLacSeq_id).getIdentity();
		URI tetR_id = get_tetR(document, tetRSeq_id).getIdentity();
		URI pLactetR_id = get_pLactetR(document, pLac_id, tetR_id, pLactetRSeq_id).getIdentity();

		URI LacI_id = get_LacI(document).getIdentity();
		URI TetR_id = get_TetR(document).getIdentity();

		URI ptet_id = get_ptet(document, ptetSeq_id).getIdentity();
		URI lacI_id = get_lacI(document, lacISeq_id).getIdentity();
		URI ptetlacI_id = get_ptetlacI(document, ptet_id, lacI_id, ptetlacISeq_id).getIdentity();

		FunctionalComponent LacIIn =
				SBOLTestUtils.createFunctionalComponent("LacIIn",
						AccessType.PUBLIC, DirectionType.INPUT, LacI_id);
		URI LacIIn_id = LacIIn.getIdentity();

		FunctionalComponent LacIInv =
				SBOLTestUtils.createFunctionalComponent("LacIInv",
						AccessType.PUBLIC, DirectionType.INPUT, pLactetR_id);
		URI LacIInv_id = LacIInv.getIdentity();

		FunctionalComponent TetROut =
				SBOLTestUtils.createFunctionalComponent("TetROut",
						AccessType.PUBLIC, DirectionType.OUTPUT, TetR_id);
		URI TetROut_id = TetROut.getIdentity();

		FunctionalComponent TetRInv =
				SBOLTestUtils.createFunctionalComponent("TetRInv",
						AccessType.PRIVATE, DirectionType.NONE, ptetlacI_id);
		//		URI TetRInv_id = TetRInv.getIdentity();

		List<FunctionalComponent> LacI_Inv_functionalComponents = new ArrayList<FunctionalComponent>();
		LacI_Inv_functionalComponents.add(LacIIn);
		LacI_Inv_functionalComponents.add(LacIInv);
		LacI_Inv_functionalComponents.add(TetROut);
		LacI_Inv_functionalComponents.add(TetRInv);

		//ModuleDefinition
		//		get_LacIIn(document, ptetlacI_id);
		URI LacI_Inv_id = get_LacI_Inv(document, LacI_Inv_functionalComponents,
				LacI_id, pLactetR_id, TetR_id, ptetlacI_id,
				LacIIn.getIdentity(), LacIInv.getIdentity(),
				TetROut.getIdentity(), TetRInv.getIdentity()).getIdentity();

		List<FunctionalComponent> TetR_Inv_functionalComponents = new ArrayList<FunctionalComponent>();
		FunctionalComponent TetRIn =
				SBOLTestUtils.createFunctionalComponent("TetRIn",
						AccessType.PUBLIC, DirectionType.INPUT, TetR_id);
		URI TetRIn_id = TetRIn.getIdentity();

		FunctionalComponent LacIOut =
				SBOLTestUtils.createFunctionalComponent("LacIOut",
						AccessType.PUBLIC, DirectionType.OUTPUT, LacI_id);
		URI LacIOut_id = LacIOut.getIdentity();

		//		FunctionalComponent TetRInv =
		//				SBOLTestUtils.createFunctionalComponent("TetRInv",
		//						AccessType.PRIVATE, DirectionType.NONE, ptetlacI_id);
		TetR_Inv_functionalComponents.add(TetRIn);
		TetR_Inv_functionalComponents.add(LacIOut);
		TetR_Inv_functionalComponents.add(TetRInv);

		URI TetR_Inv_id = get_TetR_Inv(document, TetR_Inv_functionalComponents,
				TetRIn.getIdentity(), LacIOut.getIdentity(), TetRInv.getIdentity()).getIdentity();


		//Model
		URI ToggleModel_id = get_ToggleModel(document).getIdentity();
		Set<URI> models = new HashSet<URI>();
		models.add(ToggleModel_id);

		//ModuleDefinition
		URI Toggle_id = get_Toggle(document, models,
				LacI_id, TetR_id, LacI_Inv_id, TetR_Inv_id,
				LacIIn_id, TetROut_id, LacIOut_id, TetRIn_id).getIdentity();

		Collection myParts = SBOLTestUtils.createCollection(document, "myParts", annotations);

		//		myParts.addMember(get_topLevel(SBOL2Doc_test).getIdentity());
		myParts.addMember(pLacSeq_id);
		myParts.addMember(tetRSeq_id);
		myParts.addMember(pLactetRSeq_id);

		myParts.addMember(pLac_id);
		myParts.addMember(tetR_id);
		myParts.addMember(pLactetR_id);

		myParts.addMember(LacI_Inv_id);

		myParts.addMember(LacI_id);
		myParts.addMember(TetR_id);

		myParts.addMember(ptetSeq_id);
		myParts.addMember(lacISeq_id);
		myParts.addMember(ptetlacISeq_id);

		myParts.addMember(ptet_id);
		myParts.addMember(lacI_id);
		myParts.addMember(ptetlacI_id);

		myParts.addMember(TetR_Inv_id);

		myParts.addMember(Toggle_id);
		myParts.addMember(ToggleModel_id);

		myParts.addMember(SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel").getIdentity());

		runTest("test/data/writeTesterString_v1.3.rdf", document);
	}*/

	public ComponentDefinition get_pLac(SBOLDocument document, URI pLacSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		return SBOLTestUtils.createComponentDefinition(document, "pLac", type, role,
				pLacSeq_id, null, null, null);
	}

	public ComponentDefinition get_tetR(SBOLDocument document, URI tetRSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("CDS");
		return SBOLTestUtils.createComponentDefinition(document, "tetR", type, role,
				tetRSeq_id, null, null, null);
	}

	public ComponentDefinition get_pLactetR(SBOLDocument document, URI pLac_id, URI tetR_id, URI pLactetRSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Gene");

		List<Component> subComponents = new ArrayList<Component>();
		//get_P & get_C 319
		Component P = SBOLTestUtils.createComponent("P", AccessType.PUBLIC, pLac_id);
		Component C = SBOLTestUtils.createComponent("C", AccessType.PUBLIC, tetR_id);
		subComponents.add(P);
		subComponents.add(C);

		List<SequenceConstraint> sequenceConstraints = new ArrayList<SequenceConstraint>();
		//get_struct_constraint 321
		SequenceConstraint struct_constraint =
				SBOLTestUtils.createSequenceConstraint("struct_constraint",
						P.getIdentity(), C.getIdentity(), SequenceConstraint.RestrictionType.PRECEDES);
		sequenceConstraints.add(struct_constraint);

		return SBOLTestUtils.createComponentDefinition(document, "pLactetR", type, role,
				pLactetRSeq_id, null, sequenceConstraints, subComponents);
	}

	public ComponentDefinition get_LacI(SBOLDocument document)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");

		return SBOLTestUtils.createComponentDefinition(document, "LacI", type, role,
				null, null, null, null);
	}

	public ComponentDefinition get_TetR(SBOLDocument document)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");

		return SBOLTestUtils.createComponentDefinition(document, "TetR", type, role,
				null, null, null, null);
	}

	public ComponentDefinition get_ptet(SBOLDocument document, URI ptetSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		return SBOLTestUtils.createComponentDefinition(document, "ptet", type, role,
				ptetSeq_id, null, null, null);
	}

	public ComponentDefinition get_lacI(SBOLDocument document, URI tetRSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("CDS");
		return SBOLTestUtils.createComponentDefinition(document, "lacI", type, role,
				null, null, null, null);
	}

	public ComponentDefinition get_ptetlacI(SBOLDocument document, URI ptet_id, URI lacI_id, URI ptetlacISeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Gene");

		List<Component> subComponents = new ArrayList<Component>();
		//get_T & get_L 514
		Component T = SBOLTestUtils.createComponent("T", AccessType.PUBLIC, ptet_id);
		Component L = SBOLTestUtils.createComponent("L", AccessType.PUBLIC, lacI_id);
		subComponents.add(T);
		subComponents.add(L);

		List<SequenceAnnotation> sequenceAnnotations = new ArrayList<SequenceAnnotation>();

		Range r1 = new Range(URI.create("p2_structAnnotate_range"), 0, 10);
		Range r2 = new Range(URI.create("c2_structAnnotate_range"), 11, 20);
		r1.setOrientation(Sbol2Terms.Orientation.inline);
		r2.setOrientation(Sbol2Terms.Orientation.inline);

		SequenceAnnotation t_structAnnotate =
				SBOLTestUtils.createSequenceAnnotation("p2_structAnnotate", r1);
		SequenceAnnotation l_structAnnotate =
				SBOLTestUtils.createSequenceAnnotation("c2_structAnnotate", r2);

		sequenceAnnotations.add(t_structAnnotate);
		sequenceAnnotations.add(l_structAnnotate);

		return SBOLTestUtils.createComponentDefinition(document, "ptetlacI", type, role,
				ptetlacISeq_id, sequenceAnnotations, null, subComponents);
	}

	//	public FunctionalComponent get_LacIIn(SBOLDocument document, URI LacI_id)
	//	{
	//		return SBOLTestUtils.createFunctionalComponent("LacIIn",
	//				AccessType.PUBLIC, DirectionType.INPUT, LacI_id);
	//	}

	//	public FunctionalComponent get_LacInv(SBOLDocument document, URI pLactetR_id)
	//	{
	//		return SBOLTestUtils.createFunctionalComponent("LacInv",
	//				AccessType.PUBLIC, DirectionType.INPUT, pLactetR_id);
	//	}

	//	public FunctionalComponent get_TetROut(SBOLDocument document, URI TetR_id)
	//	{
	//		return SBOLTestUtils.createFunctionalComponent("TetROut",
	//				AccessType.PUBLIC, DirectionType.OUTPUT, TetR_id);
	//	}

	//	public FunctionalComponent get_TetRInv(SBOLDocument document, URI ptetlacI_id)
	//	{
	//		return SBOLTestUtils.createFunctionalComponent("TetRInv",
	//				AccessType.PRIVATE, DirectionType.NONE, ptetlacI_id);
	//	}


	public ModuleDefinition get_LacI_Inv(SBOLDocument document,
			List<FunctionalComponent> functionalComponents,
			URI LacI_id, URI pLactetR_id,
			URI TetR_id, URI ptetlacI_id,
			URI LacIIn_id, URI LacIInv_id, URI TetROut_id, URI TetRInv_id)
	{
		Set<URI> roles = SBOLTestUtils.getSetPropertyURI("Inverter");

		List<Interaction> interactions = new ArrayList<Interaction>();

		Set<URI> p1a_roles = SBOLTestUtils.getSetPropertyURI("repressor"); //365
		Set<URI> p2a_roles = SBOLTestUtils.getSetPropertyURI("repressed"); //373
		Set<URI> p3a_roles = SBOLTestUtils.getSetPropertyURI("produced");
		Set<URI> p4a_roles = SBOLTestUtils.getSetPropertyURI("producer");

		Set<URI> interact1a_type = SBOLTestUtils.getSetPropertyURI("repression");
		Set<URI> interact2a_type = SBOLTestUtils.getSetPropertyURI("production");

		List<Participation> interact1a_participations = new ArrayList<Participation>();
		Participation p1a = SBOLTestUtils.createParticipation("p1a", p1a_roles, LacIIn_id);
		Participation p2a = SBOLTestUtils.createParticipation("p2a", p2a_roles, LacIInv_id);
		interact1a_participations.add(p1a);
		interact1a_participations.add(p2a);

		List<Participation> interact2a_participations = new ArrayList<Participation>(); //409
		Participation p3a = SBOLTestUtils.createParticipation("p3a", p3a_roles, TetROut_id);
		Participation p4a = SBOLTestUtils.createParticipation("p4a", p4a_roles, TetRInv_id);
		interact2a_participations.add(p1a);
		interact2a_participations.add(p2a);

		//get_interact1a & get_interact2a 424
		Interaction interact1a = SBOLTestUtils.createInteraction("interact1", interact1a_type, interact1a_participations);
		Interaction interact2a = SBOLTestUtils.createInteraction("interact2a", interact2a_type, interact2a_participations);
		interactions.add(interact1a);
		interactions.add(interact2a);

		List<Annotation> annotations = new ArrayList<Annotation>();
		Annotation a = new Annotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
				new Turtle("turtleString"));
		annotations.add(a);

		return SBOLTestUtils.createModuleDefinition(document, "LacI_Inv",
				roles,
				functionalComponents,
				interactions,
				null, //Module
				null, //Model
				annotations);

	}

	public ModuleDefinition get_TetR_Inv(SBOLDocument document,
			List<FunctionalComponent> functionalComponents,
			URI TetRIn_id, URI TetRInv_id, URI LacIOut_id)
	{
		Set<URI> roles = SBOLTestUtils.getSetPropertyURI("Inverter");

		List<Interaction> interactions = new ArrayList<Interaction>();

		Set<URI> p1b_roles = SBOLTestUtils.getSetPropertyURI("repressor"); //365
		Set<URI> p2b_roles = SBOLTestUtils.getSetPropertyURI("repressed"); //373
		Set<URI> p3b_roles = SBOLTestUtils.getSetPropertyURI("produced");
		Set<URI> p4b_roles = SBOLTestUtils.getSetPropertyURI("producer");

		Set<URI> interact1b_type = SBOLTestUtils.getSetPropertyURI("repression");
		Set<URI> interact2b_type = SBOLTestUtils.getSetPropertyURI("production");

		List<Participation> interact1b_participations = new ArrayList<Participation>();
		Participation p1b = SBOLTestUtils.createParticipation("p1b", p1b_roles, TetRIn_id);
		Participation p2b = SBOLTestUtils.createParticipation("p2b", p2b_roles, TetRInv_id); //TODO: check on TetRInv_id
		interact1b_participations.add(p1b);
		interact1b_participations.add(p2b);

		List<Participation> interact2b_participations = new ArrayList<Participation>(); //409
		Participation p3b = SBOLTestUtils.createParticipation("p3b", p3b_roles, LacIOut_id);
		Participation p4b = SBOLTestUtils.createParticipation("p4b", p4b_roles, TetRInv_id); //TODO: check on TetRInv_id
		interact2b_participations.add(p3b);
		interact2b_participations.add(p4b);

		//get_interact1a & get_interact2a 424
		Interaction interact1b = SBOLTestUtils.createInteraction("interact1b", interact1b_type, interact1b_participations);
		Interaction interact2b = SBOLTestUtils.createInteraction("interact2b", interact2b_type, interact2b_participations);
		interactions.add(interact1b);
		interactions.add(interact2b);

		List<Annotation> annotations = new ArrayList<Annotation>();
		Annotation a = new Annotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
				new Turtle("turtleString"));
		annotations.add(a);

		return SBOLTestUtils.createModuleDefinition(document, "TetR_Inv",
				roles,
				functionalComponents,
				interactions,
				null, //Module
				null, //Model
				annotations);

	}

	public Model get_ToggleModel (SBOLDocument document)
	{
		return SBOLTestUtils.createModel(document, "ToggleModel", null);
	}

	public ModuleDefinition get_Toggle(SBOLDocument document, Set<URI> models,
			URI LacI_id, URI TetR_id, URI LacI_Inv_id, URI TetR_Inv_id,
			URI LacIIn_id, URI TetROut_id,
			URI LacIOut_id, URI TetRIn_id)
	{

		/*
		 * LacIIn_id, TetROut_id, LacIOut_id, TetRIn_id
		 */
		Set<URI> roles = SBOLTestUtils.getSetPropertyURI("Toggle_role");
		List<Annotation> annotations = new ArrayList<Annotation>();
		Annotation a = new Annotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
				new Turtle("turtleString"));
		annotations.add(a);

		List<FunctionalComponent> functionalComponents = new ArrayList<FunctionalComponent>();

		FunctionalComponent LacISp = SBOLTestUtils.createFunctionalComponent("LacISp",
				AccessType.PUBLIC, DirectionType.INPUT, LacI_id);
		URI LacISp_id = LacISp.getIdentity();

		FunctionalComponent TetRSp = SBOLTestUtils.createFunctionalComponent("TetRSp",
				AccessType.PUBLIC, DirectionType.INPUT, TetR_id);
		URI TetRSp_id = TetRSp.getIdentity();

		functionalComponents.add(TetRSp);
		functionalComponents.add(LacISp);

		List<MapsTo> Inv1_maps = new ArrayList<MapsTo>();
		MapsTo Inv1a = SBOLTestUtils.createMapTo("Inv1a", RefinementType.USELOCAL,
				LacISp_id, LacIIn_id);
		MapsTo Inv2a_TetRSp = SBOLTestUtils.createMapTo("Inv2a_TetRSp", RefinementType.USELOCAL,
				TetRSp_id, TetROut_id);
		Inv1_maps.add(Inv1a);
		Inv1_maps.add(Inv2a_TetRSp);

		List<MapsTo> Inv2_maps = new ArrayList<MapsTo>();
		MapsTo Inv1b = SBOLTestUtils.createMapTo("Inv1b", RefinementType.USELOCAL,
				LacISp_id, LacIOut_id);
		MapsTo Inv2b = SBOLTestUtils.createMapTo("Inv2b", RefinementType.USELOCAL,
				TetRSp_id, TetRIn_id);
		Inv2_maps.add(Inv1b);
		Inv2_maps.add(Inv2b);

		List<Module> submodules = new ArrayList<Module>();
		Module Inv1 = SBOLTestUtils.createModuleData(document, "Inv1", LacI_Inv_id, Inv1_maps);
		Module Inv2 = SBOLTestUtils.createModuleData(document, "Inv2", TetR_Inv_id, Inv2_maps);
		submodules.add(Inv1);
		submodules.add(Inv2);

		ModuleDefinition modDef = SBOLTestUtils.createModuleDefinition(document, "Toggle",
				roles,
				functionalComponents,
				null,
				submodules,
				models,
				annotations);

		return modDef;
	}


	public abstract void runTest(final String fileName, final SBOLDocument expected)
			throws Exception;

}
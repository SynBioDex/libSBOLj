package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.junit.Test;
import org.sbolstandard.core2.FunctionalComponent.DirectionType;
import org.sbolstandard.core2.MapsTo.RefinementType;
import org.sbolstandard.core2.SequenceConstraint.RestrictionType;
import org.sbolstandard.core2.abstract_classes.ComponentInstance;
import org.sbolstandard.core2.abstract_classes.ComponentInstance.AccessType;

/**
 * @author Tramy Nguyen
 *
 */
public abstract class SBOLAbstractTests {

	@Test
	public void test_JSONFile() throws Exception
	{
		SBOLDocument document=new SBOLDocument();
		SBOLWriter.writeJSON(document, (System.out));
	}

	public void test_TurtleFile() throws Exception
	{
		SBOLDocument document=new SBOLDocument();
		SBOLWriter.writeTurtle(document, (System.out));
	}

	@Test
	public void test_moduleDef_withFunctionalComponent() throws Exception
	{
		SBOLDocument document=new SBOLDocument();

		Set<URI> roles = SBOLTestUtils.getSetPropertyURI("Inverter");
		ModuleDefinition moduleDefinition=document.createModuleDefinition(URI.create("testmodule"),roles);
		FunctionalComponent fc=moduleDefinition.createFunctionalComponent(
				URI.create("fc1"),
				ComponentInstance.AccessType.PUBLIC,
				URI.create("cd1"),
				FunctionalComponent.DirectionType.NONE);
		SBOLWriter.writeRDF(document,(System.out));
	}

	@Test
	public void test_Annotations() throws Exception
	{
		SBOLDocument document = new SBOLDocument();

		List<Annotation> collection_annotations = new ArrayList<Annotation>();
		collection_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "coll_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createCollection(document, "myParts", collection_annotations);

		List<Annotation> model_annotations = new ArrayList<Annotation>();
		model_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "model_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createModel(document, "someModel", model_annotations);

		List<Annotation> moduleDefinition_annotations = new ArrayList<Annotation>();
		moduleDefinition_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "modDef_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createModuleDefinition(document, "someModDef", null, null, null, null, null, moduleDefinition_annotations);

		List<Annotation> sequence_annotations = new ArrayList<Annotation>();
		sequence_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "seq_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createSequence(document, "someSeq", sequence_annotations); //TODO failing right here because can't create a sequence

		List<Annotation> componentDefinition_annotations = new ArrayList<Annotation>();
		componentDefinition_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "compDef_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createComponentDefinition(document, "someCompDef", null, null, null, null, null, null, componentDefinition_annotations);

		List<Annotation> genericTopLevel_annotations = new ArrayList<Annotation>();
		genericTopLevel_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "genericTopLev_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createGenericTopLevel(document, "someGenericTopLevel", genericTopLevel_annotations);

		List<Annotation> component_annotations = new ArrayList<Annotation>();
		component_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "component_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createComponent("someComponent", AccessType.PUBLIC, null, component_annotations);

		List<Annotation> sequenceAnnotation_annotations = new ArrayList<Annotation>();
		sequenceAnnotation_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "seqAnnot_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createSequenceAnnotation("someSequence", null, sequenceAnnotation_annotations);

		List<Annotation> sequenceConstraint_annotations = new ArrayList<Annotation>();
		sequenceConstraint_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "seqCon_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createSequenceConstraint("someSequenceConstraint", null, null, RestrictionType.PRECEDES, sequenceConstraint_annotations);

		//TODO: unable to create a componentInstance for testing.
		//		List<Annotation> componentInstance_annotations = new ArrayList<Annotation>();
		//		componentInstance_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "compInstance_annot1"),
		//				new Turtle("turtleString")));


		List<Annotation> mapsTo_annotations = new ArrayList<Annotation>();
		mapsTo_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "mapsTo_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createMapTo("someMapsTo", RefinementType.USELOCAL, null, null, mapsTo_annotations);

		List<Annotation> location_annotations = new ArrayList<Annotation>();
		location_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "location_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createLocation("someLocation", new Range(URI.create("someRange1"), 0, 10), location_annotations);

		List<Annotation> multiRange_annotations = new ArrayList<Annotation>();
		multiRange_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "multiRange_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createLocation("someMultiRange1", new MultiRange(URI.create("someMultiRange")), multiRange_annotations);

		List<Annotation> range_annotations = new ArrayList<Annotation>();
		range_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "range_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createLocation("someRange2", new Range(URI.create("someRange"), 0, 10), range_annotations);

		List<Annotation> cut_annotations = new ArrayList<Annotation>();
		cut_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "cut_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createLocation("someCut", new Cut(URI.create("someCut"), 10), cut_annotations);

		List<Annotation> genericLocation_annotations = new ArrayList<Annotation>();
		genericLocation_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "genericLoc_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createGenericTopLevel(document, "someGenericTopLevel", genericLocation_annotations);

		List<Annotation> module_annotations = new ArrayList<Annotation>();
		module_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "module_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createModule("someModule", null, module_annotations);

		List<Annotation> interaction_annotations = new ArrayList<Annotation>();
		interaction_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "interaction_annot1"),
				new Turtle("turtleString")));
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		List<Participation> interact_part = new ArrayList<Participation>();
		Participation p1a = SBOLTestUtils.createParticipation("p1a", null, null);
		interact_part.add(p1a);
		SBOLTestUtils.createInteraction("someInteraction", type, interact_part, interaction_annotations);

		List<Annotation> functionalComponent_annotations = new ArrayList<Annotation>();
		functionalComponent_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "funcComp_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createFunctionalComponent("someFunctionalComp", AccessType.PUBLIC, DirectionType.INOUT, null, functionalComponent_annotations);

		List<Annotation> participation_annotations = new ArrayList<Annotation>();
		participation_annotations.add(new Annotation(new QName("http://myannotation.org", "thisAnnotation", "participation_annot1"),
				new Turtle("turtleString")));
		SBOLTestUtils.createParticipation("someParticipation", null, participation_annotations);

	}

	@Test
	public void test_singleCollection_no_Members() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

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
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		SBOLTestUtils.createCollection(document, "myPart1", null);
		SBOLTestUtils.createCollection(document, "myPart2", null);
		SBOLTestUtils.createCollection(document, "myPart3", null);

		runTest("test/data/multipleCollections_no_Members.rdf", document);
	}

	@Test
	public void test_singleGenericTopLevel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel", null);
		runTest("test/data/singleGenericTopLevel.rdf", document);
	}

	@Test
	public void test_multipleGenericTopLevel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel1", null);
		SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel2", null);
		SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel3", null);
		runTest("test/data/multipleGenericTopLevel.rdf", document);
	}

	@Test
	public void test_singleModel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createModel(document, "ToggleModel", null);
		runTest("test/data/singleModel.rdf", document);
	}


	@Test
	public void test_singleSequence() throws Exception
	{
		SBOLDocument document = new SBOLDocument();

		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createSequence(document,"pLacSeq", null);

		runTest("test/data/singleSequence.rdf", document);
	}

	@Test
	public void test_multipleSquences() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		SBOLTestUtils.createSequence(document,"pLacSeq", null);
		SBOLTestUtils.createSequence(document,"tetRSeq", null);
		SBOLTestUtils.createSequence(document,"pLactetRSeq", null);
		runTest("test/data/multipleSequences.rdf", document);
	}

	@Test
	public void test_single_emptyModuleDefinition() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

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
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		SBOLTestUtils.createComponentDefinition(document, "pLac", type, role,
				null, null, null, null, null);

		runTest("test/data/singleComponentDefinition.rdf", document);
	}

	@Test
	public void test_singleCompDef_withSeq() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		URI s = SBOLTestUtils.createSequence(document, "pLacSeq", null).getIdentity(); //TODO: throwing error right here because unable to create a sequence object but rather a null object
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		SBOLTestUtils.createComponentDefinition(document, "pLac", type, role,
				s, null, null, null, null);

		runTest("test/data/singleCompDef_withSeq.rdf", document);
	}

	@Test
	public void test_singleFunctionalComponent() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.addNamespaceBinding(URI.create("http://myannotation.org"), "annot");
		document.addNamespaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");
		ComponentDefinition compdef = SBOLTestUtils.createComponentDefinition(document, "LacIIn", type, role,
				null, null, null, null, null);
		URI compDef_id = compdef.getIdentity(); //TODO: error right here due to returning a null object thus unable to get identity.

		AccessType accessType 		= AccessType.PUBLIC;
		DirectionType directionType = DirectionType.INPUT;
		SBOLTestUtils.createFunctionalComponent("LacIIn", accessType, directionType, compDef_id, null);

		runTest("test/data/singleFunctionalComponent.rdf", document);
	}

	//	|------------------------------------TOGGLE SWITCH------------------------------------|
	//	|		- double check on correct use of URIs										  |
	//	|																					  |
	//	|-------------------------------------------------------------------------------------|
	//	@Test
	//	public void test_ToggleSwitch() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
	//		document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
	//
	//		List<Annotation> annotations = new ArrayList<Annotation>();
	//		Annotation a = new Annotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
	//				new Turtle("turtleString"));
	//		annotations.add(a);
	//
	//		//Sequence
	//		URI pLacSeq_id = SBOLTestUtils.createSequence(document,"pLacSeq").getIdentity();
	//		URI tetRSeq_id = SBOLTestUtils.createSequence(document,"tetRSeq").getIdentity();
	//		URI pLactetRSeq_id = SBOLTestUtils.createSequence(document,"pLactetRSeq").getIdentity();
	//
	//		URI ptetSeq_id = SBOLTestUtils.createSequence(document,"ptetSeq").getIdentity();
	//		URI lacISeq_id = SBOLTestUtils.createSequence(document,"lacISeq").getIdentity();
	//		URI ptetlacISeq_id = SBOLTestUtils.createSequence(document,"ptetlacISeq").getIdentity();
	//
	//		//ComponentDefintion
	//		URI pLac_id = get_pLac(document, pLacSeq_id).getIdentity();
	//		URI tetR_id = get_tetR(document, tetRSeq_id).getIdentity();
	//		URI pLactetR_id = get_pLactetR(document, pLac_id, tetR_id, pLactetRSeq_id).getIdentity();
	//
	//		URI LacI_id = get_LacI(document).getIdentity();
	//		URI TetR_id = get_TetR(document).getIdentity();
	//
	//		URI ptet_id = get_ptet(document, ptetSeq_id).getIdentity();
	//		URI lacI_id = get_lacI(document, lacISeq_id).getIdentity();
	//		URI ptetlacI_id = get_ptetlacI(document, ptet_id, lacI_id, ptetlacISeq_id).getIdentity();
	//
	//		//ModuleDefinition
	//		//		get_LacIIn(document, ptetlacI_id);
	//		//		URI LacI_Inv_id = get_LacI_Inv(document, LacI_id, pLactetR_id, TetR_id, ptetlacI_id).getIdentity();
	//
	//		Collection myParts = SBOLTestUtils.createCollection(document, "myParts", annotations);
	//		myParts.addMember(pLacSeq_id);
	//		myParts.addMember(tetRSeq_id);
	//		myParts.addMember(pLactetRSeq_id);
	//
	//		myParts.addMember(pLac_id);
	//		myParts.addMember(tetR_id);
	//		myParts.addMember(pLactetR_id);
	//
	//		//		myParts.addMember(LacI_Inv_id); //TODO
	//
	//		myParts.addMember(LacI_id);
	//		myParts.addMember(TetR_id);
	//
	//		myParts.addMember(ptetSeq_id);
	//		myParts.addMember(lacISeq_id);
	//		myParts.addMember(ptetlacISeq_id);
	//
	//		myParts.addMember(ptet_id);
	//		myParts.addMember(lacI_id);
	//		myParts.addMember(ptetlacI_id);
	//
	//		//		myParts.addMember(get_TetR_Inv(SBOL2Doc_test).getIdentity());
	//		//
	//		//		myParts.addMember(get_Toggle(SBOL2Doc_test).getIdentity());
	//		//		myParts.addMember(get_ToggleModel(SBOL2Doc_test).getIdentity());
	//
	//		myParts.addMember(SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel").getIdentity());
	//
	//		runTest("test/data/sampleToggleSwitch.rdf", document);
	//	}

	public ComponentDefinition get_pLac(SBOLDocument document, URI pLacSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		return SBOLTestUtils.createComponentDefinition(document, "pLac", type, role,
				pLacSeq_id, null, null, null, null);
	}

	public ComponentDefinition get_tetR(SBOLDocument document, URI tetRSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("CDS");
		return SBOLTestUtils.createComponentDefinition(document, "tetR", type, role,
				tetRSeq_id, null, null, null, null);
	}

	public ComponentDefinition get_pLactetR(SBOLDocument document, URI pLac_id, URI tetR_id, URI pLactetRSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Gene");

		List<Component> subComponents = new ArrayList<Component>();
		//get_P & get_C 319
		Component P = SBOLTestUtils.createComponent("P", AccessType.PUBLIC, pLac_id, null);
		Component C = SBOLTestUtils.createComponent("C", AccessType.PUBLIC, tetR_id, null);
		subComponents.add(P);
		subComponents.add(C);

		List<SequenceConstraint> sequenceConstraints = new ArrayList<SequenceConstraint>();
		//get_struct_constraint 321
		SequenceConstraint struct_constraint =
				SBOLTestUtils.createSequenceConstraint("struct_constraint",
						P.getIdentity(), C.getIdentity(), SequenceConstraint.RestrictionType.PRECEDES, null);
		sequenceConstraints.add(struct_constraint);

		return SBOLTestUtils.createComponentDefinition(document, "pLactetR", type, role,
				pLactetRSeq_id, null, sequenceConstraints, subComponents, null);
	}
	//
	//	public ComponentDefinition get_LacI(SBOLDocument document)
	//	{
	//		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
	//		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");
	//
	//		return SBOLTestUtils.createComponentDefinition(document, "LacI", type, role,
	//				null, null, null, null);
	//	}
	//
	//	public ComponentDefinition get_TetR(SBOLDocument document)
	//	{
	//		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
	//		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");
	//
	//		return SBOLTestUtils.createComponentDefinition(document, "TetR", type, role,
	//				null, null, null, null);
	//	}
	//
	//	public ComponentDefinition get_ptet(SBOLDocument document, URI ptetSeq_id)
	//	{
	//		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
	//		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
	//		return SBOLTestUtils.createComponentDefinition(document, "ptet", type, role,
	//				ptetSeq_id, null, null, null);
	//	}
	//
	//	public ComponentDefinition get_lacI(SBOLDocument document, URI tetRSeq_id)
	//	{
	//		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
	//		Set<URI> role = SBOLTestUtils.getSetPropertyURI("CDS");
	//		return SBOLTestUtils.createComponentDefinition(document, "lacI", type, role,
	//				null, null, null, null);
	//	}
	//
	//	public ComponentDefinition get_ptetlacI(SBOLDocument document, URI ptet_id, URI lacI_id, URI ptetlacISeq_id)
	//	{
	//		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
	//		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Gene");
	//
	//		List<Component> subComponents = new ArrayList<Component>();
	//		//get_T & get_L 514
	//		Component T = SBOLTestUtils.createComponent("T", AccessType.PUBLIC, ptet_id);
	//		Component L = SBOLTestUtils.createComponent("L", AccessType.PUBLIC, lacI_id);
	//		subComponents.add(T);
	//		subComponents.add(L);
	//
	//		List<SequenceAnnotation> sequenceAnnotations = new ArrayList<SequenceAnnotation>();
	//
	//		Range r1 = new Range(URI.create("p2_structAnnotate_range"), 0, 10);
	//		Range r2 = new Range(URI.create("c2_structAnnotate_range"), 11, 20);
	//		r1.setOrientation(Sbol2Terms.Orientation.inline);
	//		r2.setOrientation(Sbol2Terms.Orientation.inline);
	//
	//		SequenceAnnotation t_structAnnotate =
	//				SBOLTestUtils.createSequenceAnnotation("p2_structAnnotate", r1);
	//		SequenceAnnotation l_structAnnotate =
	//				SBOLTestUtils.createSequenceAnnotation("c2_structAnnotate", r2);
	//
	//		sequenceAnnotations.add(t_structAnnotate);
	//		sequenceAnnotations.add(l_structAnnotate);
	//
	//		return SBOLTestUtils.createComponentDefinition(document, "ptetlacI", type, role,
	//				ptetlacISeq_id, sequenceAnnotations, null, subComponents);
	//	}
	//
	//	public FunctionalComponent get_LacIIn(SBOLDocument document, URI LacI_id)
	//	{
	//		return SBOLTestUtils.createFunctionalComponent("LacIIn",
	//				AccessType.PUBLIC, DirectionType.INPUT, LacI_id);
	//	}
	//
	//	public FunctionalComponent get_LacInv(SBOLDocument document, URI pLactetR_id)
	//	{
	//		return SBOLTestUtils.createFunctionalComponent("LacInv",
	//				AccessType.PUBLIC, DirectionType.INPUT, pLactetR_id);
	//	}
	//
	//	public FunctionalComponent get_TetROut(SBOLDocument document, URI TetR_id)
	//	{
	//		return SBOLTestUtils.createFunctionalComponent("TetROut",
	//				AccessType.PUBLIC, DirectionType.OUTPUT, TetR_id);
	//	}
	//
	//	public FunctionalComponent get_TetRInv(SBOLDocument document, URI ptetlacI_id)
	//	{
	//		return SBOLTestUtils.createFunctionalComponent("TetRInv",
	//				AccessType.PRIVATE, DirectionType.NONE, ptetlacI_id);
	//	}


	//TODO:
	//	public ModuleDefinition get_LacI_Inv(SBOLDocument document,
	//			List<FunctionalComponent> functionalComponents,
	//			URI LacI_id, URI pLactetR_id,
	//			URI TetR_id, URI ptetlacI_id)
	//	{
	//		Set<URI> roles = SBOLTestUtils.getSetPropertyURI("Inverter");
	//
	//		List<Interaction> interactions = new ArrayList<Interaction>();
	//
	//		Set<URI> p1a_roles = SBOLTestUtils.getSetPropertyURI("repressor"); //365
	//		Set<URI> p2a_roles = SBOLTestUtils.getSetPropertyURI("repressed"); //373
	//		Set<URI> p3a_roles = SBOLTestUtils.getSetPropertyURI("produced");
	//		Set<URI> p4a_roles = SBOLTestUtils.getSetPropertyURI("producer");
	//
	//		Set<URI> interact1a_type = SBOLTestUtils.getSetPropertyURI("repression");
	//		Set<URI> interact2a_type = SBOLTestUtils.getSetPropertyURI("production");
	//
	//		//TODO: remove these and past in it's id to the method
	//		URI p1a_FuncComp_id =
	//				SBOLTestUtils.createFunctionalComponent("LacIIn",
	//						AccessType.PUBLIC, DirectionType.INPUT, LacI_id).getIdentity();
	//		URI p2a_FuncComp_id =
	//				SBOLTestUtils.createFunctionalComponent("LacInv",
	//						AccessType.PUBLIC, DirectionType.INPUT, pLactetR_id).getIdentity();
	//		URI p3a_FuncComp_id =
	//				SBOLTestUtils.createFunctionalComponent("TetROut",
	//						AccessType.PUBLIC, DirectionType.OUTPUT, TetR_id).getIdentity();
	//		URI p4a_FuncComp_id =
	//				SBOLTestUtils.createFunctionalComponent("TetRInv",
	//						AccessType.PRIVATE, DirectionType.NONE, ptetlacI_id).getIdentity();
	//
	//		List<Participation> interact1a_participations = new ArrayList<Participation>();
	//		Participation p1a = SBOLTestUtils.createParticipation("p1a", p1a_roles, p1a_FuncComp_id);
	//		Participation p2a = SBOLTestUtils.createParticipation("p2a", p2a_roles, p2a_FuncComp_id);
	//		interact1a_participations.add(p1a);
	//		interact1a_participations.add(p2a);
	//
	//		List<Participation> interact2a_participations = new ArrayList<Participation>(); //409
	//		Participation p3a = SBOLTestUtils.createParticipation("p3a", p3a_roles, p3a_FuncComp_id);
	//		Participation p4a = SBOLTestUtils.createParticipation("p4a", p4a_roles, p4a_FuncComp_id);
	//		interact2a_participations.add(p1a);
	//		interact2a_participations.add(p2a);
	//
	//		//get_interact1a & get_interact2a 424
	//		Interaction interact1a = SBOLTestUtils.createInteraction("interact1", interact1a_type, interact1a_participations);
	//		Interaction interact2a = SBOLTestUtils.createInteraction("interact2a", interact2a_type, interact2a_participations);
	//		interactions.add(interact1a);
	//		interactions.add(interact2a);
	//
	//		List<Annotation> annotations = new ArrayList<Annotation>();
	//		Annotation a = new Annotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
	//				new Turtle("turtleString"));
	//		annotations.add(a);
	//
	//		return SBOLTestUtils.createModuleDefinition(document, "LacI_Inv",
	//				roles,
	//				functionalComponents,
	//				interactions,
	//				null,
	//				null,
	//				annotations);
	//
	//	}


	public abstract void runTest(final String fileName, final SBOLDocument expected)
			throws Exception;

}

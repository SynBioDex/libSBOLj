package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.junit.Test;

public abstract class SBOLAbstractTests {


	@Test
	public void test_singleCollection_no_Members() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		List<Annotation> annotations = new ArrayList<Annotation>();
		Annotation a = new Annotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
				new Turtle("turtleString"));
		annotations.add(a);
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");

		SBOLTestUtils.createCollection(document, "myParts", null/*annotations*/);

		runTest("test/data/singleCollection.rdf", document);
	}


	//@Test
	//	public void test_singleGenericTopLevel() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel");
	//		runTest("test/data/singleGenericTopLevel.rdf", document);
	//	}
	//
	//	@Test
	//	public void test_singleModel() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		SBOLTestUtils.createModel(document, "ToggleModel",
	//				"ToggleModel_source", "ToggleModel_language", "ToggleModel_framework",
	//				SBOLTestUtils.getSetPropertyURI("ToggleModel_role"));
	//		runTest("test/data/singleGenericTopLevel.rdf", document);
	//	}
	//
	//
	//	@Test
	//	public void test_singleSequence() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		SBOLTestUtils.createSequence(document,"pLacSeq");
	//
	//		runTest("test/data/single_pLacSeq.rdf", document);
	//	}
	//
	//	//	@Test
	//	//	public void test_singleModuleDefinition() throws Exception
	//	//	{
	//	//		SBOLDocument document = new SBOLDocument();
	//	//		SBOLTestUtils.createModuleDefinition(document, "LacI_Inv",
	//	//				"Inverter",
	//	//				functionalComponent_data,
	//	//				interactionData,
	//	//				submodule_data,
	//	//				model_data,
	//	//				annotations);
	//	//
	//	//		runTest("test/data/single_pLacSeq.rdf", document);
	//	//	}
	//
	//	@Test
	//	public void test_emptyCompDef() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		SBOLTestUtils.createComponentDefinition(document, "pLac", "DNA", "Promoter",
	//				null, null, null, null);
	//	}
	//
	//	@Test
	//	public void test_CompDefinition_with_sequence() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		URI s = SBOLTestUtils.createSequence(document, "pLacSeq").getIdentity();
	//		SBOLTestUtils.createComponentDefinition(document, "pLac", "DNA", "Promoter",
	//				s, null, null, null);
	//	}
	//
	//	//	@Test
	//	//	public void test_ComponentDefinition() throws Exception
	//	//	{
	//	//		SBOLDocument document = new SBOLDocument();
	//	//
	//	//		URI sequenceId = SBOLTestUtils.createSequence(document, "pLacSeq").getIdentity();
	//	//
	//	//		List<SequenceAnnotation> sequenceAnnotations ;
	//	//		List<SequenceConstraint> sequenceConstraints;
	//	//		List<Component> subComponents;
	//	//
	//	//		Range range = new Range(locationURI, 0, 10);
	//	//		range.setOrientation(Sbol2Terms.Orientation.inline);
	//	//		Location location = range;
	//	//		SBOLTestUtils.createSequenceAnnotation("", ref_component, location);
	//	//
	//	//
	//	//		SBOLTestUtils.createComponentDefinition(document, "pLactetR", "DNA", "Gene",
	//	//				sequenceId, null, null, null);
	//	//	}
	//
	//
	//
	//	@Test
	//	public void test_emptyFunctionalComponent() throws Exception
	//	{
	//		SBOLTestUtils.createFunctionalComponent("LacIIn", "public", "input", null);
	//	}
	//
	//	@Test
	//	public void test_single_FunctComp_with_emptyCompDef() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		SBOLTestUtils.createComponentDefinition(document,
	//				"LacI", "Protein", "Transcriptionfactor", null,
	//				null, null, null);
	//
	//		URI instantiatedComponent = document.getComponentDefinition(URI.create("LacI")).getIdentity();
	//		SBOLTestUtils.createFunctionalComponent("LacIIn", "public", "input", instantiatedComponent);
	//	}
	//
	//
	//	/**
	//	 * List<FunctionalComponent> functionalComponent_data,
	//			List<Interaction> interactionData,
	//			List<Module> submodule_data,
	//			Set<URI> model_data,
	//			List<Annotation> annotations
	//	 * @throws Exception
	//	 */
	//
	//	@Test
	//	public void test_multipleSquences() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		SBOLTestUtils.createSequence(document,"pLacSeq");
	//		SBOLTestUtils.createSequence(document,"tetRSeq");
	//		SBOLTestUtils.createSequence(document,"pLactetRSeq");
	//		runTest("test/data/multiple_sequences.rdf", document);
	//	}
	//
	//	@Test
	//	public void test_ToggleSwitch() throws Exception
	//	{
	//		SBOLDocument document = new SBOLDocument();
	//		SBOLTestUtils.createSequence(document, "pLacSeq");
	//		SBOLTestUtils.createSequence(document, "tetRSeq");
	//		URI sequenceId = SBOLTestUtils.createSequence(document, "pLactetRSeq").getIdentity();
	//		SBOLTestUtils.createComponentDefinition(document, "pLac",
	//				"DNA", "Promoter",
	//				sequenceId, null, null, null);
	//
	//		runTest("test/data/singleGenericTopLevel.rdf", document);
	//	}

	public abstract void runTest(final String fileName, final SBOLDocument expected)
			throws Exception;

}
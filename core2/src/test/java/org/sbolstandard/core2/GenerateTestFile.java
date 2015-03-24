package org.sbolstandard.core2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.abstract_classes.ComponentInstance.AccessType;

public class GenerateTestFile
{

	public static void main(String[] args)
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


		Collection myParts = SBOLTestUtils.createCollection(document, "myParts", annotations);
		myParts.addMember(pLacSeq_id);
		myParts.addMember(tetRSeq_id);
		myParts.addMember(pLactetRSeq_id);

		myParts.addMember(pLac_id);
		myParts.addMember(tetR_id);
		myParts.addMember(pLactetR_id);

		myParts.addMember(LacI_id);
		myParts.addMember(TetR_id);

		myParts.addMember(ptetSeq_id);
		myParts.addMember(lacISeq_id);
		myParts.addMember(ptetlacISeq_id);

		myParts.addMember(ptet_id);
		myParts.addMember(lacI_id);
		myParts.addMember(ptetlacI_id);

		myParts.addMember(SBOLTestUtils.createGenericTopLevel(document, "GenericTopLevel").getIdentity());


		writeRdfFile(document, "sampleToggleSwitch.rdf");

	}

	public static ComponentDefinition get_pLac(SBOLDocument document, URI pLacSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		return SBOLTestUtils.createComponentDefinition(document, "pLac", type, role,
				pLacSeq_id, null, null, null);
	}

	public static ComponentDefinition get_tetR(SBOLDocument document, URI tetRSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("CDS");
		return SBOLTestUtils.createComponentDefinition(document, "tetR", type, role,
				tetRSeq_id, null, null, null);
	}

	public static ComponentDefinition get_pLactetR(SBOLDocument document, URI pLac_id, URI tetR_id, URI pLactetRSeq_id)
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

	public static ComponentDefinition get_LacI(SBOLDocument document)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");

		return SBOLTestUtils.createComponentDefinition(document, "LacI", type, role,
				null, null, null, null);
	}

	public static ComponentDefinition get_TetR(SBOLDocument document)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");

		return SBOLTestUtils.createComponentDefinition(document, "TetR", type, role,
				null, null, null, null);
	}

	public static ComponentDefinition get_ptet(SBOLDocument document, URI ptetSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		return SBOLTestUtils.createComponentDefinition(document, "ptet", type, role,
				ptetSeq_id, null, null, null);
	}

	public static ComponentDefinition get_lacI(SBOLDocument document, URI tetRSeq_id)
	{
		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("CDS");
		return SBOLTestUtils.createComponentDefinition(document, "lacI", type, role,
				tetRSeq_id, null, null, null);
	}

	public static ComponentDefinition get_ptetlacI(SBOLDocument document, URI ptet_id, URI lacI_id, URI ptetlacISeq_id)
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

	public static void writeRdfFile(SBOLDocument document, String fileName)
	{
		try {
			SBOLWriter.writeRdf(document, new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

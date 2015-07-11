package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.sbolstandard.core.SBOLValidationException;

import uk.ac.ncl.intbio.core.io.CoreIoException;

public class writeTester {

	private static SBOLDocument SBOL2Doc_test = new SBOLDocument();

	private static String  rdfString   = "writeTesterString_v1.3.rdf";
	private static String  rdfFile 	   = "writeTesterFile_v1.3.rdf";

	private static String JsonString   = "writeTesterString_v1.3.json";
	private static String JsonFile 	   = "writeTesterFile_v1.3.json";

	private static String TurtleString = "writeTesterString_v1.3.ttl";
	private static String TurtleFile   = "writeTesterFile_v1.3.ttl";
	
	private static String version = "1.0";

	//private static String  fileName   = "single_singleCollection.rdf";

	/**
	 * Top level types
	 *
	 */

	public static void main( String[] args ) throws XMLStreamException, FactoryConfigurationError, CoreIoException
	{
		get_myParts(SBOL2Doc_test);
		/*
		SBOLDocument sbolDoc = new SBOLDocument();
		sbolDoc.setDefaultURIprefix("http://www.some.org");
		ComponentDefinition cd = SBOL2Doc_test.getComponentDefinition("pLactetR", "1.0");
		sbolDoc.createCopy(cd,"pLactetR2","2.0");
		cd = SBOL2Doc_test.getComponentDefinition("ptetlacI", "1.0");
		sbolDoc.createCopy(cd,"ptetLacI2","2.0");
		ModuleDefinition md = SBOL2Doc_test.getModuleDefinition("Toggle", "1.0");
		sbolDoc.createCopy(md,"Toggle2","2.0");
		*/
		writeRdfOutputStream(SBOL2Doc_test);

		//		writeJsonOutputStream();
		//		writeTurtleOutputStream();

		//		writeRdfString();
		//		writeJsonString();
		//		writeTurtleString();

		//writeRdfFile();

		//writeJsonFile();
		//writeTurtleFile();
	}

	public static void writeRdfOutputStream(SBOLDocument SBOL2Doc_test)
	{
		try {
			SBOLWriter.writeRDF(SBOL2Doc_test,(System.out));
			SBOL2Doc_test = SBOLTestUtils.writeAndRead(SBOL2Doc_test);
			SBOLWriter.writeRDF(SBOL2Doc_test,"/Users/myers/Downloads/writeTest.rdf");//(System.out));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (CoreIoException e) {
			e.printStackTrace();
		}
		catch (SBOLValidationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeJsonOutputStream()
	{
		try {
			SBOLWriter.writeJSON(SBOL2Doc_test,(System.out));
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void writeTurtleOutputStream()
	{
		try {
			SBOLWriter.writeTurtle(SBOL2Doc_test,(System.out));
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void writeRdfString()
	{
		try {
			SBOLWriter.writeRDF(SBOL2Doc_test, rdfString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeJsonString()
	{
		try {
			SBOLWriter.writeJSON(SBOL2Doc_test, JsonString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeTurtleString()
	{
		try {
			SBOLWriter.writeTurtle(SBOL2Doc_test, TurtleString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}


	public static void writeJsonFile()
	{
		try {
			SBOLWriter.writeJSON(SBOL2Doc_test, new File(JsonFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeTurtleFile()
	{
		try {
			SBOLWriter.writeJSON(SBOL2Doc_test, new File(TurtleFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeRdfFile()
	{
		try {
			SBOLWriter.writeRDF(SBOL2Doc_test, new File(rdfFile)); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void get_myParts (SBOLDocument SBOL2Doc_test)
	{
		SBOL2Doc_test.setDefaultURIprefix("http://www.async.ece.utah.edu");
		SBOL2Doc_test.setComplete(true);
		SBOL2Doc_test.setCreateDefaults(true);
		SBOL2Doc_test.addNamespace(URI.create("http://myannotation.org/"), "annot");
		SBOL2Doc_test.addNamespace(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		Collection myParts = createCollection(SBOL2Doc_test,
				getData("myParts", version),
				getAnnotation_List(createAnnotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),"stringAnnotation"),
					createAnnotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),URI.create("http://www.someannotation.org"))));

		myParts.addMember(get_LacI(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_TetR(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_ptetSeq(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_lacISeq(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_ptetlacISeq(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_ptet(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_lacI(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_ptetlacI(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_TetR_Inv(SBOL2Doc_test).getIdentity());
		
		myParts.addMember(get_pLacSeq(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_tetRSeq(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_pLactetRSeq(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_pLac(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_tetR(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_pLactetR(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_LacI_Inv(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_Toggle(SBOL2Doc_test).getIdentity());
		myParts.addMember(get_ToggleModel(SBOL2Doc_test).getIdentity());

		myParts.addMember(get_topLevel(SBOL2Doc_test).getIdentity());
	}

	private static Identified get_topLevel (SBOLDocument SBOL2Doc_test)
	{

		return createTopLevel(SBOL2Doc_test, getData("GenericTopLevel",version));
	}

	private static Sequence get_pLacSeq (SBOLDocument SBOL2Doc_test)
	{
		return createSequenceData(SBOL2Doc_test,
				getData("pLacSeq",version,"pLacSeq_element"),
				Sequence.IUPAC_DNA);
	}

	private static Sequence get_tetRSeq (SBOLDocument SBOL2Doc_test)
	{
		return createSequenceData(SBOL2Doc_test,
				getData("tetRSeq",version,"tetRSeq_element"),
				Sequence.IUPAC_DNA);
	}

	private static Sequence get_pLactetRSeq (SBOLDocument SBOL2Doc_test)
	{
		return createSequenceData(SBOL2Doc_test,
				getData("pLactetRSeq",version,"pLactetRSeq_element"),
				Sequence.IUPAC_DNA);
	}

	private static ComponentDefinition get_pLac (SBOLDocument SBOL2Doc_test)
	{
		return createComponentDefinitionData(SBOL2Doc_test,
				getSetURI(ComponentDefinition.DNA),
				getSetURI(SequenceOntology.PROMOTER),
				getData("pLac",version),
				"pLacSeq");
	}

	private static ComponentDefinition get_tetR (SBOLDocument SBOL2Doc_test)
	{
		return createComponentDefinitionData(SBOL2Doc_test,
				getSetURI(ComponentDefinition.DNA),
				getSetURI(SequenceOntology.CDS),
				getData("tetRCDS",version),
				"tetRSeq");
	}

//	private static Component get_P (ComponentDefinition cd)
//	{
//		return createComponentData(cd,getData("pLac", "public"),"pLac");
//	}
//
//	private static Component get_C (ComponentDefinition cd)
//	{
//		return createComponentData(cd,getData("tetRCDS", "public"),"tetRCDS");
//	}

	private static SequenceConstraint get_struct_constraint (ComponentDefinition cd)
	{
		return createSequenceConstraintData(
				cd,
				getData("struct_constraint"),
				"pLac","tetRCDS",
				RestrictionType.PRECEDES);
	}

	private static ComponentDefinition get_pLactetR (SBOLDocument SBOL2Doc_test)
	{
		ComponentDefinition cd = createComponentDefinitionData(SBOL2Doc_test,
				getSetURI(ComponentDefinition.DNA),
				getSetURI(SequenceOntology.ENGINEERED_GENE),
				getData("pLactetR",version),
				"pLactetRSeq");
		//get_P(cd);
		//get_C(cd);
		get_struct_constraint(cd);
		return cd;
	}

	private static ComponentDefinition get_LacI (SBOLDocument SBOL2Doc_test)
	{
		return createComponentDefinitionData(SBOL2Doc_test,
				getSetURI(ComponentDefinition.PROTEIN),
				null,
				getData("LacI",version),
				null);
	}

	private static ComponentDefinition get_TetR (SBOLDocument SBOL2Doc_test)
	{
		return createComponentDefinitionData(SBOL2Doc_test,
				getSetURI(ComponentDefinition.PROTEIN),
				null,
				getData("TetR",version),
				null);
	}

//	private static FunctionalComponent get_LacIIn (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
//	{
//		return createFunctionalComponentData(md,getData("LacI","public", "input"),"LacI");
//	}
//
//	private static FunctionalComponent get_TetROut (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
//	{
//		return createFunctionalComponentData(md,getData("TetR","public", "output"),"TetR");
//	}
//
//	private static FunctionalComponent get_LacIInv (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
//	{
//		return createFunctionalComponentData(md,getData("pLactetR","private", "none"),"pLactetR");
//	}

	private static void get_p1a (SBOLDocument SBOL2Doc_test, ModuleDefinition md, Interaction i, String displayId)
	{
		createParticipationData(i,displayId,getSetURI(SystemsBiologyOntology.INHIBITOR),"LacI");
	}

	private static void get_p2a (SBOLDocument SBOL2Doc_test, ModuleDefinition md, Interaction i, String displayId)
	{
		createParticipationData(i, displayId,getSetURI(SystemsBiologyOntology.PROMOTER),"LacI");
	}

	private static void get_p4a (SBOLDocument SBOL2Doc_test, ModuleDefinition md, Interaction i, String displayId)
	{
		createParticipationData(i, displayId,getSetURI(SystemsBiologyOntology.PROMOTER),"TetR");
	}

	private static void get_p3a (SBOLDocument SBOL2Doc_test, ModuleDefinition md, Interaction i, String displayId)
	{
		createParticipationData(i, displayId,getSetURI(SystemsBiologyOntology.PRODUCT),"TetR");
	}

	private static Interaction get_interact1a (SBOLDocument SBOL2Doc_test, ModuleDefinition md)
	{
		Interaction i = createInteractionData(
				md,
				getData("interact1"),
				getSetURI(SystemsBiologyOntology.GENETIC_SUPPRESSION));
		get_p1a(SBOL2Doc_test, md, i, "p1a");
		get_p2a(SBOL2Doc_test, md, i, "p2a");
		return i;
	}

	private static Interaction get_interact2a (SBOLDocument SBOL2Doc_test, ModuleDefinition md)
	{
		Interaction i = createInteractionData(
				md,
				getData("interact2"),
				getSetURI(SystemsBiologyOntology.GENETIC_PRODUCTION));
		get_p4a(SBOL2Doc_test, md, i, "p4a");
		get_p3a(SBOL2Doc_test, md, i, "p3a");
		return i;
	}

	private static ModuleDefinition get_LacI_Inv (SBOLDocument SBOL2Doc_test)
	{
		ModuleDefinition md = createModuleDefinitionData(SBOL2Doc_test,
				getSetOfURI("Inverter"),
				//getSetPropertyURI("Inverter"),
				getData("LacI_Inv",version));
		//get_LacIIn(SBOL2Doc_test,md);
		//get_TetROut(SBOL2Doc_test,md);
		//get_LacIInv(SBOL2Doc_test,md);
		//get_TetRInv(SBOL2Doc_test,md);
		get_interact1a(SBOL2Doc_test,md);
		get_interact2a(SBOL2Doc_test,md);
		getAnnotation_List(createAnnotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),createTurtle()));
		return md;
	}


	// ------------------------------ CREATING TetR_Inverter ------------------------------
	private static Sequence get_ptetSeq (SBOLDocument SBOL2Doc_test)
	{
		return createSequenceData(SBOL2Doc_test,
				getData("ptetSeq",version,"ptetSeq_element"),
				Sequence.IUPAC_DNA);
	}

	private static Sequence get_lacISeq (SBOLDocument SBOL2Doc_test)
	{
		return createSequenceData(SBOL2Doc_test,
				getData("lacISeq",version,"lacISeq_element"),
				Sequence.IUPAC_DNA);
	}

	private static Sequence get_ptetlacISeq (SBOLDocument SBOL2Doc_test)
	{
		return createSequenceData(SBOL2Doc_test,
				getData("ptetlacISeq",version,"ptetlacISeq_element"),
				Sequence.IUPAC_DNA);
	}

	private static ComponentDefinition get_ptet(SBOLDocument SBOL2Doc_test)
	{
		return createComponentDefinitionData(SBOL2Doc_test,
				getSetURI(ComponentDefinition.DNA),
				getSetURI(SequenceOntology.PROMOTER),
				getData("ptet",version),
				"ptetSeq");
	}

	private static ComponentDefinition get_lacI (SBOLDocument SBOL2Doc_test)
	{
		return createComponentDefinitionData(SBOL2Doc_test,
				getSetURI(ComponentDefinition.DNA),
				getSetURI(SequenceOntology.CDS),
				getData("lacICDS",version),
				"lacISeq");
	}

//	private static Component get_T (ComponentDefinition cd)
//	{
//		return createComponentData(cd,getData("ptet", "public"),"ptet");
//	}
//
//	private static Component get_L (ComponentDefinition cd)
//	{
//		return createComponentData(cd,getData("lacICDS", "public"),"lacICDS");
//	}

	private static SequenceAnnotation get_t_structAnnotate (ComponentDefinition cd)
	{
		return createSequenceAnnotationData(cd,getData("p2_structAnnotate"),"ptet",1,10,"p2_structAnnotate_range");
	}

	private static SequenceAnnotation get_l_structAnnotate (ComponentDefinition cd)
	{
		return createSequenceAnnotationData(cd,getData("c2_structAnnotate"),"lacICDS",11,20,"c2_structAnnotate_range");
	}

	private static ComponentDefinition get_ptetlacI (SBOLDocument SBOL2Doc_test)
	{
		ComponentDefinition cd = createComponentDefinitionData(SBOL2Doc_test,
				getSetURI(ComponentDefinition.DNA),
				getSetURI(SequenceOntology.ENGINEERED_GENE),
				getData("ptetlacI",version),
				"ptetlacISeq");
		//get_T(cd); 
		//get_L(cd);
		get_t_structAnnotate(cd);
		get_l_structAnnotate(cd);
		return cd;
	}

//	private static FunctionalComponent get_TetRIn (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
//	{
//		return createFunctionalComponentData(md,getData("TetR", "public", "input"),"TetR");
//	}
//
//	private static FunctionalComponent get_LacIOut (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
//	{
//		return createFunctionalComponentData(md,getData("LacI", "public", "output"),"LacI");
//	}
//
//	private static FunctionalComponent get_TetRInv (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
//	{
//		return createFunctionalComponentData(md,getData("TetR", "private", "none"),"ptetlacI");
//	}

	private static void get_p1b (SBOLDocument SBOL2Doc_test, ModuleDefinition md, Interaction i, String displayId)
	{
		createParticipationData(i,displayId,getSetURI(SystemsBiologyOntology.INHIBITOR),"TetR");
	}

	private static void get_p2b (SBOLDocument SBOL2Doc_test, ModuleDefinition md, Interaction i, String displayId)
	{
		createParticipationData(i,displayId,getSetURI(SystemsBiologyOntology.PROMOTER),"TetR");
	}

	private static void get_p4b (SBOLDocument SBOL2Doc_test, ModuleDefinition md, Interaction i, String displayId)
	{
		createParticipationData(i,displayId,getSetURI(SystemsBiologyOntology.PROMOTER),"TetR");
	}

	private static void get_p3b (SBOLDocument SBOL2Doc_test, ModuleDefinition md, Interaction i, String displayId)
	{
		createParticipationData(i,displayId,getSetURI(SystemsBiologyOntology.PRODUCT),"LacI");
	}

	private static void get_interact1b (SBOLDocument SBOL2Doc_test, ModuleDefinition md)
	{
		Interaction i = createInteractionData(
			md,
			getData("interact1b"),
			getSetURI(SystemsBiologyOntology.GENETIC_SUPPRESSION));
		get_p1b(SBOL2Doc_test, md, i, "p1b");
		get_p2b(SBOL2Doc_test, md, i, "p2b");
	}

	private static void get_interact2b (SBOLDocument SBOL2Doc_test, ModuleDefinition md)
	{
		Interaction i = createInteractionData(
			md,
			getData("interact2b"),
			getSetURI(SystemsBiologyOntology.GENETIC_PRODUCTION));
		get_p4b(SBOL2Doc_test, md, i, "p4b");
		get_p3b(SBOL2Doc_test, md, i, "p3b");
	}

	private static ModuleDefinition get_TetR_Inv (SBOLDocument SBOL2Doc_test)
	{
		ModuleDefinition md = createModuleDefinitionData(SBOL2Doc_test,
				getSetOfURI("Inverter"),
				//getSetPropertyURI("Inverter"),
				getData("TetR_Inv",version));
		//get_TetRIn(SBOL2Doc_test,md);
		//get_LacIOut(SBOL2Doc_test,md);
		//get_TetRInv(SBOL2Doc_test,md);
		get_interact1b(SBOL2Doc_test,md);
		get_interact2b(SBOL2Doc_test,md);
		getAnnotation_List(createAnnotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),createTurtle()));
		return md;
	}

	// ------------------------------ CREATING Toggle Top Module ------------------------------
//	private static FunctionalComponent get_LacISp (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
//	{
//		return createFunctionalComponentData(md,getData("LacI", "public", "input"),"LacI");
//	}
//
//	private static FunctionalComponent get_TetRSp (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
//	{
//		return createFunctionalComponentData(md,getData("TetR", "public", "input"),"TetR");
//	}

	private static void get_Inv1 (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
	{
		Module m = createModuleData(md,getData("Inv1"),"LacI_Inv");
		createMapsTo(m,"Inv1a", RefinementType.USELOCAL, "LacI","LacI");
		createMapsTo(m,"Inv2a_TetRSp", RefinementType.USELOCAL, "TetR","TetR");
	}

	private static void get_Inv2 (SBOLDocument SBOL2Doc_test,ModuleDefinition md)
	{
		Module m = createModuleData(
			md,
			getData("Inv2"),
			"TetR_Inv");
		createMapsTo(m,"Inv1b", RefinementType.USELOCAL, "LacI","LacI");
		createMapsTo(m,"Inv2b", RefinementType.USELOCAL, "TetR","TetR");
	}

	private static Model get_ToggleModel(SBOLDocument SBOL2Doc_test)
	{
		Model m = createModelData(SBOL2Doc_test,
				getData("ToggleModel",version),
				getPropertyURI("ToggleModel_source"), 
				Model.SBML, 
				SystemsBiologyOntology.CONTINUOUS_FRAMEWORK);
		return m;
	}

	private static ModuleDefinition get_Toggle (SBOLDocument SBOL2Doc_test)
	{
		ModuleDefinition md = createModuleDefinitionData(SBOL2Doc_test,
				getSetOfURI("Toggle_type"),
				//getSetPropertyURI("Toggle_role"),
				getData("Toggle",version));
		//get_LacISp(SBOL2Doc_test,md);
		//get_TetRSp(SBOL2Doc_test,md);
		get_Inv1(SBOL2Doc_test,md);
		get_Inv2(SBOL2Doc_test,md);
		Model m = get_ToggleModel(SBOL2Doc_test);
		md.addModel(m.getDisplayId(),version);
		//getAnnotation_List(md,createAnnotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),createTurtle()));
		return md;
	}


	private static void setCommonTopLevelData (TopLevel t, String name, String description)
	{
		setCommonDocumentedData(t, name, description);
	}

	private static void setCommonDocumentedData(Identified d, String name, String description)
	{
		d.setName(name);
		d.setDescription(description);
	}

	private static GenericTopLevel createTopLevel(SBOLDocument SBOL2Doc_test, List<String> topLevelData)
	{
		String displayId 	   = topLevelData.get(0);
		String version 		   = topLevelData.get(1);

		GenericTopLevel toplevel =  SBOL2Doc_test.createGenericTopLevel(displayId, version, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));
		SBOL2Doc_test.addNamespace(URI.create("urn:bbn.com:tasbe:grn"), "grn");
		List<Annotation> as = new ArrayList<>();
		Annotation a = new Annotation(new QName("urn:bbn.com:tasbe:grn", "Repressor", "grn"),"species1");
		as.add(a);
		a = new Annotation(new QName("urn:bbn.com:tasbe:grn", "Activator", "grn"),"species2");
		as.add(a);
		a = toplevel.createAnnotation(new QName("urn:bbn.com:tasbe:grn", "theReaction", "grn"), 
				new QName("urn:bbn.com:tasbe:grn","TheReaction","grn"), 
				URI.create("http://www.async.ece.utah.edu/myAnnotation"), as);
		/*
		as = a.getAnnotations();
		System.out.println(a.getQName());
		System.out.println(a.getNestedQName());
		System.out.println(a.getNestedIdentity());
		for (Annotation an : as) {
			System.out.println(an.getQName() + " = " + an.getStringValue());
		}
		*/
		setCommonTopLevelData(toplevel, displayId, displayId);
		return toplevel;
	}


	private static Collection createCollection(SBOLDocument SBOL2Doc_test, List<String> collectionData,
			List<Annotation> annotations)
	{
		String displayId	   = collectionData.get(0);
		String version  	   = collectionData.get(1);

		Collection collection = SBOL2Doc_test.createCollection(displayId,version);
		setCommonTopLevelData(collection, displayId, displayId);
		if(annotations != null)
			collection.setAnnotations(annotations);
		return collection;
	}

	private static Annotation createAnnotation(QName relation, String literal)
	{
		return new Annotation(NamedProperty(relation, literal));

	}

	private static Annotation createAnnotation(QName relation, URI value)
	{
		return new Annotation(NamedProperty(relation, value));

	}
	
	private static ComponentDefinition createComponentDefinitionData(SBOLDocument SBOL2Doc_test,
			Set<URI> type, Set<URI> roles,
			List<String> componentData,
			String structureData)
	{
		String displayId 	   = componentData.get(0);
		String version 		   = componentData.get(1);

		ComponentDefinition c = SBOL2Doc_test.getComponentDefinition(displayId,version);
		if (c==null) {
			c = SBOL2Doc_test.createComponentDefinition(displayId, version, type);
			if (roles!=null) 
				c.setRoles(roles);
			setCommonTopLevelData(c, displayId, displayId);
			if(structureData != null) {
				c.addSequence(structureData, version);
			}
		}

		return c;
	}

//	private static FunctionalComponent createFunctionalComponentData(
//			ModuleDefinition md,
//			List<String> functionalInstantiation_data,
//			String c)
//	{
//		String displayId 	   = functionalInstantiation_data.get(0);
//
//		AccessType access = null;
//		if(functionalInstantiation_data.get(1).equals("public"))
//			access = AccessType.PUBLIC;
//		else if(functionalInstantiation_data.get(1).equals("private"))
//			access = AccessType.PRIVATE;
//
//		DirectionType direction = null;
//		if(functionalInstantiation_data.get(2).equals("input"))
//			direction = DirectionType.IN;
//		else if(functionalInstantiation_data.get(2).equals("output"))
//			direction = DirectionType.OUT;
//		else if(functionalInstantiation_data.get(2).equals("inout"))
//			direction = DirectionType.INOUT;
//		else if(functionalInstantiation_data.get(2).equals("none"))
//			direction = DirectionType.NONE;
//
//		FunctionalComponent f = md.getFunctionalComponent(displayId);
//		if (f==null) {
//			f = md.createFunctionalComponent(displayId, access, c, version,	direction);
//			setCommonDocumentedData(f, displayId, displayId);
//		} 
//
//		return f;
//	}

	private static Interaction createInteractionData(
			ModuleDefinition md,
			List<String> interaction_data,
			Set<URI> type)
	{
		String displayId 	   = interaction_data.get(0);
		Interaction interaction = md.getInteraction(displayId);
		if (interaction==null) {
			interaction = md.createInteraction(displayId, type);
			setCommonDocumentedData(interaction, displayId, displayId);
		} 
		return interaction;
	}

	private static String createTurtle()
	{
		return "turtleString";
	}

	private static void createMapsTo(Module m, String displayId, RefinementType refinement,
			String pre_fi, String post_fi)
	{
		m.createMapsTo(displayId, refinement, pre_fi, post_fi);
	}


	private static Model createModelData(SBOLDocument doc, List<String> modeldata, 
			URI source, URI language, URI framework)
	{
		String displayId 	   = modeldata.get(0);
		String version 		   = modeldata.get(1);

		Model model = doc.getModel(displayId,version);
		if (model==null) {
			model = doc.createModel(displayId, version, source, language, framework);		
			setCommonTopLevelData(model, displayId, displayId);
		} 
		return model;
	}

	private static ModuleDefinition createModuleDefinitionData(SBOLDocument SBOL2Doc_test,
			Set<URI> type, /* Set<URI> roles, */
			List<String> module_data)
	{
		String displayId 	   = module_data.get(0);
		String version 		   = module_data.get(1);

		ModuleDefinition m = SBOL2Doc_test.getModuleDefinition(displayId,version);
		if (m==null) {
			m = SBOL2Doc_test.createModuleDefinition(displayId, version);
			//m.setRoles(roles);
			setCommonTopLevelData(m, displayId, displayId);
		} 
		return m;
	}

	private static Module createModuleData(
			ModuleDefinition md,
			List<String> moduleInstantiation_data,
			String m)
	{
		String displayId 	   = moduleInstantiation_data.get(0);
		
		Module modInstantiation = md.createModule(displayId, m, version);
		setCommonDocumentedData(modInstantiation, displayId, displayId);

		return modInstantiation;
	}


	private static void createParticipationData(Interaction i,
			String displayId, Set<URI> roles, String fi)
	{
		if (i.getParticipation(displayId)==null) {
			Participation p = i.createParticipation(displayId, fi);
			p.setRoles(roles);
		}
	}

	private static SequenceAnnotation createSequenceAnnotationData(
			ComponentDefinition cd,
			List<String> structuralAnnotations_data,
			String ref_component,
			int startRange, int endRange,
			String locationId)
	{
		String displayId	   = structuralAnnotations_data.get(0);

		SequenceAnnotation s = cd.getSequenceAnnotation(displayId);
		if (s==null) {
			s = cd.createSequenceAnnotation(displayId, "range", startRange, endRange, OrientationType.INLINE);
			setCommonDocumentedData(s, displayId, displayId);
			s.setComponent(ref_component);
		} 
		return s;
	}

	private static SequenceConstraint createSequenceConstraintData(
			ComponentDefinition cd,
			List<String> structuralConstraints_data,
			String subject,
			String object,
			RestrictionType restriction)
	{
		String displayId	   = structuralConstraints_data.get(0);

		SequenceConstraint s = cd.getSequenceConstraint(displayId);
		if (s==null) {
			s = cd.createSequenceConstraint(displayId, restriction, subject, object);
		} 
		return s;
	}

//	private static Component createComponentData(
//			ComponentDefinition cd,
//			List<String> structuralInstantiations_data,
//			String c)
//	{
//		String displayId	   = structuralInstantiations_data.get(0);
//
//		AccessType access = null;
//		if(structuralInstantiations_data.get(1).equals("public"))
//			access = AccessType.PUBLIC;
//		else if(structuralInstantiations_data.get(1).equals("private"))
//			access = AccessType.PRIVATE;
//
//		Component s = cd.getComponent(displayId);
//		if (s==null) {
//			s = cd.createComponent(displayId, access, c, version);
//			setCommonDocumentedData(s, displayId, displayId);
//		} 
//		return s;
//	}

	private static Sequence createSequenceData(SBOLDocument SBOL2Doc_test, List<String> structureData,
			URI encoding)
	{
		String displayId 	   = structureData.get(0);
		String version 		   = structureData.get(1);
		String element 		   = structureData.get(2);

		Sequence sequence = SBOL2Doc_test.getSequence(displayId,version);
		if (sequence==null) {
			sequence = SBOL2Doc_test.createSequence(displayId, version, element, encoding);
			setCommonTopLevelData(sequence, displayId, displayId);
		} 
		return sequence;
	}

	/**
	 * data[] = identity
	 * data[] = persistentIdentity
	 * data[] = version
	 * data[] = displayID
	 * data[] = Name
	 * data[] = Description
	 * @param data
	 * @return
	 */
	private static List<String> getData(String ... data)
	{
		List<String> list = new ArrayList<String>();
		for(String d : data)
		{
			list.add(d);
		}
		return list;
	}

	private static List<Annotation> getAnnotation_List(Annotation ... a)
	{
		return new ArrayList<Annotation>(Arrays.asList(a));
	}

	private static Set<URI> getSetOfURI(String ... appends)
	{
		Set<URI> list = new HashSet<URI>();
		for(String append : appends)
		{
			list.add(getURI(append));
		}
		return list;
	}
	
	private static Set<URI> getSetURI(URI ... appends)
	{
		Set<URI> set = new HashSet<URI>();
		for (URI append : appends) {
			set.add(append);
		}
		return set;
	}

	/*
	private static Set<URI> getSetPropertyURI(String ... appends)
	{
		Set<URI> list = new HashSet<URI>();
		for(String append : appends)
		{
			list.add(getPropertyURI(append));
		}
		return list;
	}
	*/

	private static URI getURI(String append)
	{
		return URI.create("http://www.async.ece.utah.edu/" + append);
	}

	private static URI getPropertyURI(String append)
	{
		return URI.create("http://some.ontology.org/" + append);
	}


}

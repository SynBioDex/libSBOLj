package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperty;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;
import static uk.ac.ncl.intbio.core.datatree.Datatree.QName;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//import javax.sound.midi.Sequence;
import javax.xml.namespace.QName;

import org.junit.Test;
import org.sbolstandard.core.SBOLValidationException;

/**
 * @author Goksel Misirli
 * @author Tramy Nguyen
 *
 *
 */
public abstract class SBOLAbstractTests {

	String VERSION_1_0 = "1.0";
	
	
//	@Test
//	public void test_methods() throws Exception
//	{
//		//this method tests all remove methods for all top level
//		String prURI="http://partsregistry.org";
//		String prPrefix="pr";
//		SBOLDocument document = new SBOLDocument();
//		document.setDefaultURIprefix(prURI);
//		document.setTypesInURIs(true);
//		document.addNamespace(URI.create(prURI), prPrefix);
//		
//		String prURI2="http://partsregistry.org";
//		String prPrefix2 ="pr";
//		SBOLDocument document2 = new SBOLDocument();
//		document2.setDefaultURIprefix(prURI);
//		document2.setTypesInURIs(true);
//		document2.addNamespace(URI.create(prURI), prPrefix);
//		
//		String CD_ID = "ID";
//		String CD_Version = "1.0";
//		Set<URI> CD_Types = new HashSet<URI>();
//		CD_Types.add(URI.create("www.example.com"));
//		
//		String SeqID = "ID2";
//		String SeqVersion = "1.0";
//		String SeqElements = "some_element";
//		URI SeqEncoding = URI.create("www.example2.com");
//		
//		String ComponentID = "CID";
//		String ComponentVersion = "1.0";
//		
//		String SeqAnnID = "Sequence_annotation_ID";
//		String SeqAnnLocID = "locationID";
//		
//		String SC_ID = "SequenceConstraintID";
//		String SC_SubID = "SubjectID";
//		String SC_ObjID = "ObjectID";
//		
//		String ModuleDefID = "ModuleDefinitionID";
//		String ModuleDefVersion = "1.0";
//		
//		String GTL_ID = "generictoplevelID";
//		String GTL_Version = "1.0";
//		
//		String ModelID = "ModelDisplayID";
//		String ModelVersion = "1.0";
//		URI source = URI.create("www.examplesource.com");
//		URI language = URI.create("www.examplelanguage.com");
//		URI framework = URI.create("www.exampleframework.com");
//		
//		String CollectionID = "CollectionID";
//		String CollectionVersion = "1.0";
//		
//		Sequence Seq = document.createSequence(SeqID, SeqVersion, SeqElements, SeqEncoding);	//create sequence
//		ComponentDefinition CD = document.createComponentDefinition(CD_ID, CD_Version, CD_Types);	//create component definition
//		CD.addSequence(Seq);
//		CD.createComponent(ComponentID, AccessType.PRIVATE, CD_ID, ComponentVersion);
//		CD.createSequenceAnnotation(SeqAnnID, SeqAnnLocID);
//		CD.createSequenceConstraint(SC_ID, RestrictionType.OPPOSITE_ORIENTATION_AS, SC_SubID, SC_ObjID);
//		ModuleDefinition MD = document.createModuleDefinition(ModuleDefID, ModuleDefVersion);
////		GenericTopLevel GTL = document.createGenericTopLevel(GTL_ID, GTL_Version, QName.valueOf(GTL_Version));
//		Model Mod = document.createModel(ModelID, ModelVersion, source, language, framework);
////		Collection Col = document.createCollection(CollectionID, CollectionVersion);
////		
//		document.removeSequence(Seq);
//		document.removeComponentDefinition(CD);
//		document.removeModuleDefinition(MD);
//		document.removeModel(Mod);
//		runTest("test/data/test_Methods.rdf", document, "rdf");
//	}


	@Test
	public void test_Model_remove() throws Exception
	{
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		document.addNamespace(URI.create(prURI), prPrefix);

		String M1_ID = "ID";
		String M1_Version = "1.0";
		String M1_Source = "www.example.com";
		String M1_Language = "www.example1.com";
		String M1_Framework = "www.example2.com";
		URI M1_URISource = URI.create(M1_Source);
		URI M1_URILanguage = URI.create(M1_Language);
		URI M1_URI_Framework = URI.create(M1_Framework);
		
		Model M1 = document.createModel(M1_ID, M1_Version, M1_URISource, M1_URILanguage, M1_URI_Framework);
		document.removeModel(M1);
		runTest("test/data/test_Model_remove.rdf", document, "rdf");
	}
	
	@Test
	public void test_Sequence_remove() throws Exception
	{
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		document.addNamespace(URI.create(prURI), prPrefix);
		
		String SeqID = "ID";
		String SeqVersion = "1.0";
		String SeqElements = "Element";
		URI SeqEncoding = URI.create("www.example.com");
		Sequence Seq = document.createSequence(SeqID, SeqVersion, SeqElements, SeqEncoding);
		document.removeSequence(Seq);
		runTest("test/data/test_Sequence_remove.rdf", document, "rdf");
	}
	
	@Test
	public void test_Collection_remove() throws Exception
	{
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		document.addNamespace(URI.create(prURI), prPrefix);
		
		String Col1_ID = "ID";
		String Col1_Version = "1.0";
		Collection Col1 = document.createCollection(Col1_ID, Col1_Version);
		document.removeCollection(Col1);
		runTest("test/data/test_Collection_remove.rdf", document, "rdf");
	}
	
	@Test
	public void test_ModuleDefinition_remove() throws Exception
	{
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		document.addNamespace(URI.create(prURI), prPrefix);
		
		String MD_ID = "ID";
		String MD_Version = "1.0";
		ModuleDefinition MD = document.createModuleDefinition(MD_ID, MD_Version);
		document.removeModuleDefinition(MD);
		runTest("test/data/test_ModuleDefinition_remove.rdf", document, "rdf");
	}
	
	@Test
	public void test_ComponentDefinition_remove() throws Exception
	{
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		document.addNamespace(URI.create(prURI), prPrefix);
		
		String CD_ID = "ID";
		String CD_Version = "1.0";
		Set<URI> CD_Types = new HashSet<URI>();
		CD_Types.add(URI.create("www.example.com"));
		ComponentDefinition CD = document.createComponentDefinition(CD_ID, CD_Version, CD_Types);
		document.removeComponentDefinition(CD);
		runTest("test/data/test_ComponentDefinition_remove.rdf", document, "rdf");
	}
	
	@Test
	public void test_GenericTopLevel_remove() throws Exception
	{
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		document.addNamespace(URI.create(prURI), prPrefix);
		
		String GTL_ID = "ID";
		String GTL_Version = "1.0";
		String GTL_Qname = "name";
		GenericTopLevel GTL = document.createGenericTopLevel(GTL_ID, GTL_Version, QName.valueOf(GTL_Qname));
		document.removeGenericTopLevel(GTL);
		runTest("test/data/test_GenericTopLevel_remove.rdf", document, "rdf");
	}
	
	
	@Test
	public void test_AnnotationOutput() throws Exception
	{
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();

		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);

		document.addNamespace(URI.create(prURI), prPrefix);



		ComponentDefinition promoter = document.createComponentDefinition(
				"BBa_J23119",
				"",
				new HashSet<URI>(Arrays.asList(URI.create("http://www.biopax.org/release/biopax-level3.owl#DnaRegion"))));

		promoter.addRole(SequenceOntology.PROMOTER);
		promoter.setName("J23119");
		promoter.setDescription("Constitutive promoter");

		promoter.createAnnotation(new QName(prURI, "group", prPrefix),
				"iGEM2006_Berkeley");

		promoter.createAnnotation(new QName(prURI, "experience", prPrefix),
				URI.create("http://parts.igem.org/cgi/partsdb/part_info.cgi?part_name=BBa_J23119"));

		Annotation sigmaFactor=new Annotation(QName(prURI, "sigmafactor", prPrefix),
				"//rnap/prokaryote/ecoli/sigma70");
		Annotation regulation=new Annotation(QName(prURI, "regulation", prPrefix),
				"//regulation/constitutive");
		promoter.createAnnotation(
				new QName(prURI, "information", prPrefix),
				new QName(prURI, "Information", prPrefix),
				URI.create("http://parts.igem.org/cgi/partsdb/part_info.cgi?part_name=BBa_J23119"),
				new ArrayList<Annotation>(Arrays.asList(sigmaFactor,regulation)));

		//		SBOLWriter.write(document,(System.out));
		runTest("test/data/AnnotationOutput.rdf", document, "rdf");
	}

	@Test
	public void test_CollectionOutput() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		//Collection col=document.createCollection(URI.create("http://parts.igem.org/Promoters/Catalog/Anderson"));

		document.setDefaultURIprefix("http://parts.igem.org/Promoters/Catalog");
		document.setTypesInURIs(false);
		Collection col=document.createCollection("Anderson","");

		col.setName("Anderson promoters");
		col.setDescription("The Anderson promoter collection");
		col.addMember(URI.create("http://partsregistry.org/Part:BBa_J23119"));
		col.addMember(URI.create("http://partsregistry.org/Part:BBa_J23118"));

		//		SBOLWriter.write(document,(System.out));
		runTest("test/data/CollectionOutput.rdf", document, "rdf");
	}

	@Test
	public void test_ComponentDefinitionOutput() throws Exception
	{
		String prURI="http://partsregistry.org";

		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		document.setTypesInURIs(true);
		document.addNamespace(URI.create(prURI), prPrefix);
		document.setDefaultURIprefix(prURI);
		/*Sequence seqdevice=document.createSequence(
					"BBa_F2620",
				     "",
					 "",
					URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
					);
		 */

		Sequence seqpTetR=document.createSequence(
				"BBa_R0040",
				"",
				"tccctatcagtgatagagattgacatccctatcagtgatagagatactgagcac",
				URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
				);

		Sequence seqRbs=document.createSequence(
				"BBa_B0034",
				"",
				"aaagaggagaaa",
				URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
				);

		Sequence seqCds=document.createSequence(
				"BBa_C0062",
				"",
				"atgcttatctgatatgactaaaatggtacattgtgaatattatttactcgcgatcatttatcctcattctatggttaaatctgatatttcaatcctagataattaccctaaaaaatggaggcaatattatgatgacgctaatttaataaaatatgatcctatagtagattattctaactccaatcattcaccaattaattggaatatatttgaaaacaatgctgtaaataaaaaatctccaaatgtaattaaagaagcgaaaacatcaggtcttatcactgggtttagtttccctattcatacggctaacaatggcttcggaatgcttagttttgcacattcagaaaaagacaactatatagatagtttatttttacatgcgtgtatgaacataccattaattgttccttctctagttgataattatcgaaaaataaatatagcaaataataaatcaaacaacgatttaaccaaaagagaaaaagaatgtttagcgtgggcatgcgaaggaaaaagctcttgggatatttcaaaaatattaggttgcagtgagcgtactgtcactttccatttaaccaatgcgcaaatgaaactcaatacaacaaaccgctgccaaagtatttctaaagcaattttaacaggagcaattgattgcccatactttaaaaattaataacactgatagtgctagtgtagatcac",
				URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
				);

		Sequence seqTer=document.createSequence(
				"BBa_B0015",
				"",
				"ccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata",
				URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
				);

		Sequence seqPluxR=document.createSequence(
				"BBa_R0062",
				"",
				"acctgtaggatcgtacaggtttacgcaagaaaatggtttgttatagtcgaataaa",
				URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
				);

		ComponentDefinition pTetR = document.createComponentDefinition(
				"BBa_R0040",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));

		pTetR.addRole(SequenceOntology.PROMOTER);
		pTetR.setName("BBa_R0040");
		pTetR.setDescription("TetR repressible promoter");
		pTetR.addSequence(seqpTetR.getIdentity());

		ComponentDefinition rbs = document.createComponentDefinition(
				"BBa_B0034",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));


		rbs.addRole(SequenceOntology.RIBOSOME_ENTRY_SITE);
		rbs.setName("BBa_B0034");
		rbs.setDescription("RBS based on Elowitz repressilator");
		rbs.addSequence(seqRbs.getIdentity());

		ComponentDefinition cds = document.createComponentDefinition(
				"BBa_C0062",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));
		cds.addRole(SequenceOntology.CDS);
		cds.setName("BBa_C0062");
		cds.setDescription("luxR coding sequence");
		cds.addSequence(seqCds.getIdentity());

		ComponentDefinition ter = document.createComponentDefinition(
				"BBa_B0015",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));

		ter.addRole(URI.create("http://identifiers.org/so/SO:0000141"));
		ter.setName("BBa_B0015");
		ter.setDescription("Double terminator");
		ter.addSequence(seqTer.getIdentity());

		ComponentDefinition pluxR = document.createComponentDefinition(
				"BBa_R0062",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));
		pluxR.addRole(SequenceOntology.PROMOTER);//
		pluxR.setName("BBa_R0062");
		pluxR.setDescription("LuxR inducible promoter");
		pluxR.addSequence(seqPluxR.getIdentity());


		ComponentDefinition device = document.createComponentDefinition(
				"BBa_F2620",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));
		device.addRole(URI.create("http://identifiers.org/so/SO:00001411"));//biological region
		device.setName("BBa_F2620");
		device.setDescription("3OC6HSL -> PoPS Receiver");
		//device.addSequence(seqdevice.getIdentity());

		Component comPtetR=device.createComponent("pTetR", AccessType.PUBLIC, pTetR.getIdentity());
		Component comRbs=device.createComponent("rbs", AccessType.PUBLIC,cds.getIdentity());
		Component comCds=device.createComponent("luxR", AccessType.PUBLIC, rbs.getIdentity());
		Component comTer=device.createComponent("ter", AccessType.PUBLIC, ter.getIdentity());
		Component comPluxR=device.createComponent( "pLuxR", AccessType.PUBLIC, pluxR.getIdentity());


		int start=1;
		int end=seqPluxR.getElements().length();

		SequenceAnnotation anno=device.createSequenceAnnotation("anno1", "range", start, end, OrientationType.INLINE);
		anno.setComponent(comPtetR.getIdentity());

		start=end+1;
		end=seqRbs.getElements().length() + end + 1;
		SequenceAnnotation anno2= device.createSequenceAnnotation("anno2", "range", start,end,OrientationType.INLINE);
		anno2.setComponent(comRbs.getIdentity());

		start=end+1;
		end=seqCds.getElements().length() + end + 1;
		SequenceAnnotation anno3= device.createSequenceAnnotation("anno3", "range", start,end,OrientationType.INLINE);
		anno3.setComponent(comCds.getIdentity());

		start=end+1;
		end=seqTer.getElements().length() + end + 1;
		SequenceAnnotation anno4= device.createSequenceAnnotation("anno4", "range", start,end,OrientationType.INLINE);
		anno4.setComponent(comTer.getIdentity());

		start=end+1;
		end=seqPluxR.getElements().length() + end + 1;
		SequenceAnnotation anno5= device.createSequenceAnnotation("anno5", "range", start,end,OrientationType.INLINE);
		anno5.setComponent(comPluxR.getIdentity());

		//			SBOLWriter.write(document,(System.out));
		runTest("test/data/ComponentDefinitionOutput.rdf", document, "rdf");
	}


	@Test
	public void test_CutExample() throws Exception
	{
		String prURI="http://partsregistry.org";
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		ComponentDefinition promoter = document.createComponentDefinition(
				"BBa_J23119",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));
		promoter.addRole(SequenceOntology.PROMOTER);
		promoter.addRole(URI.create("http://identifiers.org/so/SO:0000613"));

		promoter.setName("J23119 promoter");
		promoter.setDescription("Constitutive promoter");
		promoter.setWasDerivedFrom(URI.create("http://partsregistry.org/Part:BBa_J23119"));

		document.setDefaultURIprefix(prURI);
		Sequence seq=document.createSequence(
				"BBa_J23119",
				"",
				"ttgacagctagctcagtcctaggtataatgctagc",
				URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
				);
		seq.setWasDerivedFrom(URI.create("http://parts.igem.org/Part:BBa_J23119:Design"));
		promoter.addSequence(seq.getIdentity());

		//promoter.createSequenceAnnotation("cut", 10);
		promoter.createSequenceAnnotation("cutat10", "cut", 10, OrientationType.INLINE);
		promoter.createSequenceAnnotation("cutat12", "cut", 12, OrientationType.INLINE);

		//			SBOLWriter.write(document,(System.out));
		runTest("test/data/CutExample.rdf", document, "rdf");
	}

	@Test
	public void test_GenericTopLevelOutput() throws Exception
	{
		String myAppURI="http://www.myapp.org";
		String myAppPrefix="myapp";
		String prURI="http://www.partsregistry.org";

		SBOLDocument document = new SBOLDocument();
		document.addNamespace(URI.create(myAppURI) , myAppPrefix);

		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);

		GenericTopLevel topLevel=document.createGenericTopLevel(
				"datasheet1",
				"",
				new QName("http://www.myapp.org", "Datasheet", myAppPrefix)
				);
		topLevel.setName("Datasheet 1");

		topLevel.createAnnotation(new QName(myAppURI, "characterizationData", myAppPrefix),
				URI.create(myAppURI + "/measurement/1"));


		topLevel.createAnnotation(new QName(myAppURI, "transcriptionRate", myAppPrefix), "1");


		ComponentDefinition promoter = document.createComponentDefinition(
				"BBa_J23119",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));

		promoter.addRole(SequenceOntology.PROMOTER);
		promoter.setName("J23119");
		promoter.setDescription("Constitutive promoter");

		promoter.createAnnotation(new QName(myAppURI, "datasheet", myAppPrefix), topLevel.getIdentity());
		promoter.setWasDerivedFrom(URI.create("http://www.partsregistry.org/Part:BBa_J23119"));

		runTest("test/data/GenericTopLevelOutput.rdf", document, "rdf");
	}

	@Test
	public void test_ModelOutput() throws Exception
	{
		SBOLDocument document = new SBOLDocument();

		document.setTypesInURIs(false);

		document.setDefaultURIprefix("http://www.sbolstandard.org/examples");

		Model model=document.createModel(
				"pIKE_Toggle_1",
				"",
				URI.create("http://virtualparts.org/part/pIKE_Toggle_1"),
				URI.create("http://identifiers.org/edam/format_2585"),
				SystemsBiologyOntology.CONTINUOUS_FRAMEWORK);
		model.setName("pIKE_Toggle_1 toggle switch");


		//		SBOLWriter.write(document,(System.out));
		runTest("test/data/ModelOutput.rdf", document, "rdf");
	}


	@Test
	public void test_ModuleDefinitionOutput() throws Exception
	{
		SBOLDocument document = new SBOLDocument();

		setDefaultNameSpace(document, SBOLTestUtils.pr.getNamespaceURI());
		ComponentDefinition gfp     = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("BBa_E0040"),"gfp", ComponentDefinition.DNA, SequenceOntology.CDS, "gfp coding sequence");
		ComponentDefinition tetR    = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("BBa_C0040"),"tetR", ComponentDefinition.DNA, SequenceOntology.CDS, "tetR coding sequence");
		ComponentDefinition lacI    = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("BBa_C0012"),"lacI", ComponentDefinition.DNA, SequenceOntology.CDS, "lacI coding sequence");
		ComponentDefinition placI   = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("BBa_R0010"), "pLacI", ComponentDefinition.DNA, SequenceOntology.PROMOTER, "pLacI promoter");
		ComponentDefinition ptetR   = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("BBa_R0040"),"pTetR", ComponentDefinition.DNA, SequenceOntology.PROMOTER, "pTet promoter");
		ComponentDefinition rbslacI = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("BBa_J61101"), "BBa_J61101 RBS",ComponentDefinition.DNA, SequenceOntology.RIBOSOME_ENTRY_SITE, "RBS1"); //TODO: RIBOSOME_ENTRY_SITE is RBS?
		ComponentDefinition rbstetR = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("BBa_J61120"), "BBa_J61101 RBS",ComponentDefinition.DNA, SequenceOntology.RIBOSOME_ENTRY_SITE, "RBS2");
		ComponentDefinition rbsgfp  = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("BBa_J61130"), "BBa_J61101 RBS",ComponentDefinition.DNA, SequenceOntology.RIBOSOME_ENTRY_SITE, "RBS2");

		setDefaultNameSpace(document, SBOLTestUtils.uniprot.getNamespaceURI());
		ComponentDefinition GFP  = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.uniprot.withLocalPart("P42212"), "GFP",ComponentDefinition.PROTEIN, SystemsBiologyOntology.PRODUCT, "GFP protein");
		ComponentDefinition TetR = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.uniprot.withLocalPart("Q6QR72"), "TetR",ComponentDefinition.PROTEIN, SystemsBiologyOntology.INHIBITOR, "TetR protein");
		ComponentDefinition LacI = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.uniprot.withLocalPart("P03023"),"LacI", ComponentDefinition.PROTEIN, SystemsBiologyOntology.INHIBITOR, "LacI protein");

		setDefaultNameSpace(document, SBOLTestUtils.pr.getNamespaceURI());
		ComponentDefinition lacITerminator = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("ECK120029600"),"ECK120029600", ComponentDefinition.DNA, SequenceOntology.TERMINATOR, "Terminator1");
		ComponentDefinition tetRTerminator = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.pr.withLocalPart("ECK120033736"), "ECK120033736",ComponentDefinition.DNA, SequenceOntology.TERMINATOR, "Terminator2");

		setDefaultNameSpace(document, SBOLTestUtils.vpr.getNamespaceURI());
		ComponentDefinition tetRInverter = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.vpr.withLocalPart("pIKELeftCassette_1"), "TetR Inverter", ComponentDefinition.DNA, SequenceOntology.ENGINEERED_GENE, "TetR Inverter");
		ComponentDefinition lacIInverter = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.vpr.withLocalPart("pIKERightCassette_1"), "LacI Inverter", ComponentDefinition.DNA, SequenceOntology.ENGINEERED_GENE, "LacI Inverter");
		ComponentDefinition toggleSwitch = SBOLTestUtils.createComponenDefinition(document, SBOLTestUtils.vpr.withLocalPart("pIKE_Toggle_1"), "LacI/TetR Toggle Swicth", ComponentDefinition.DNA, SequenceOntology.ENGINEERED_GENE, "LacI/TetR Toggle Swicth");

		//tetR inverter sequences
		SBOLTestUtils.addPRSequence(document, ptetR,"tccctatcagtgatagagattgacatccctatcagtgatagagatactgagcac");
		SBOLTestUtils.addPRSequence(document, rbslacI,"aaagacaggacc");
		SBOLTestUtils.addPRSequence(document, lacI,"atggtgaatgtgaaaccagtaacgttatacgatgtcgcagagtatgccggtgtctcttatcagaccgtttcccgcgtggtgaaccaggccagccacgtttctgcgaaaacgcgggaaaaagtggaagcggcgatggcggagctgaattacattcccaaccgcgtggcacaacaactggcgggcaaacagtcgttgctgattggcgttgccacctccagtctggccctgcacgcgccgtcgcaaattgtcgcggcgattaaatctcgcgccgatcaactgggtgccagcgtggtggtgtcgatggtagaacgaagcggcgtcgaagcctgtaaagcggcggtgcacaatcttctcgcgcaacgcgtcagtgggctgatcattaactatccgctggatgaccaggatgccattgctgtggaagctgcctgcactaatgttccggcgttatttcttgatgtctctgaccagacacccatcaacagtattattttctcccatgaagacggtacgcgactgggcgtggagcatctggtcgcattgggtcaccagcaaatcgcgctgttagcgggcccattaagttctgtctcggcgcgtctgcgtctggctggctggcataaatatctcactcgcaatcaaattcagccgatagcggaacgggaaggcgactggagtgccatgtccggttttcaacaaaccatgcaaatgctgaatgagggcatcgttcccactgcgatgctggttgccaacgatcagatggcgctgggcgcaatgcgcgccattaccgagtccgggctgcgcgttggtgcggatatctcggtagtgggatacgacgataccgaagacagctcatgttatatcccgccgttaaccaccatcaaacaggattttcgcctgctggggcaaaccagcgtggaccgcttgctgcaactctctcagggccaggcggtgaagggcaatcagctgttgcccgtctcactggtgaaaagaaaaaccaccctggcgcccaatacgcaaaccgcctctccccgcgcgttggccgattcattaatgcagctggcacgacaggtttcccgactggaaagcgggcaggctgcaaacgacgaaaactacgctttagtagcttaataa");
		SBOLTestUtils.addPRSequence(document, lacITerminator,"ttcagccaaaaaacttaagaccgccggtcttgtccactaccttgcagtaatgcggtggacaggatcggcggttttcttttctcttctcaa");

		//lacI inverter sequences
		SBOLTestUtils.addPRSequence(document, placI,"tccctatcagtgatagagattgacatccctatcagtgatagagatactgagcac");
		SBOLTestUtils.addPRSequence(document, rbstetR,"aaagacaggacc");
		SBOLTestUtils.addPRSequence(document, tetR,"atgtccagattagataaaagtaaagtgattaacagcgcattagagctgcttaatgaggtcggaatcgaaggtttaacaacccgtaaactcgcccagaagctaggtgtagagcagcctacattgtattggcatgtaaaaaataagcgggctttgctcgacgccttagccattgagatgttagataggcaccatactcacttttgccctttagaaggggaaagctggcaagattttttacgtaataacgctaaaagttttagatgtgctttactaagtcatcgcgatggagcaaaagtacatttaggtacacggcctacagaaaaacagtatgaaactctcgaaaatcaattagcctttttatgccaacaaggtttttcactagagaatgcattatatgcactcagcgctgtggggcattttactttaggttgcgtattggaagatcaagagcatcaagtcgctaaagaagaaagggaaacacctactactgatagtatgccgccattattacgacaagctatcgaattatttgatcaccaaggtgcagagccagccttcttattcggccttgaattgatcatatgcggattagaaaaacaacttaaatgtgaaagtgggtccgctgcaaacgacgaaaactacgctttagtagcttaataa");
		SBOLTestUtils.addPRSequence(document, rbsgfp,"aaagaaacgaca");
		SBOLTestUtils.addPRSequence(document, gfp,"atgcgtaaaggagaagaacttttcactggagttgtcccaattcttgttgaattagatggtgatgttaatgggcacaaattttctgtcagtggagagggtgaaggtgatgcaacatacggaaaacttacccttaaatttatttgcactactggaaaactacctgttccatggccaacacttgtcactactttcggttatggtgttcaatgctttgcgagatacccagatcatatgaaacagcatgactttttcaagagtgccatgcccgaaggttatgtacaggaaagaactatatttttcaaagatgacgggaactacaagacacgtgctgaagtcaagtttgaaggtgatacccttgttaatagaatcgagttaaaaggtattgattttaaagaagatggaaacattcttggacacaaattggaatacaactataactcacacaatgtatacatcatggcagacaaacaaaagaatggaatcaaagttaacttcaaaattagacacaacattgaagatggaagcgttcaactagcagaccattatcaacaaaatactccaattggcgatggccctgtccttttaccagacaaccattacctgtccacacaatctgccctttcgaaagatcccaacgaaaagagagaccacatggtccttcttgagtttgtaacagctgctgggattacacatggcatggatgaactatacaaataataa");
		SBOLTestUtils.addPRSequence(document, tetRTerminator,"ttcagccaaaaaacttaagaccgccggtcttgtccactaccttgcagtaatgcggtggacaggatcggcggttttcttttctcttctcaa");

		SBOLTestUtils.addSubComponents(document, tetRInverter, ptetR,rbslacI,lacI,lacITerminator);
		SBOLTestUtils.addSubComponents(document, lacIInverter, placI,rbstetR,tetR,rbsgfp,gfp,tetRTerminator);
		SBOLTestUtils.addSubComponents(document, toggleSwitch, tetRInverter,lacIInverter);

		/*ModuleDefinition laciInverterModuleDef=document.createModuleDefinition(toURI(example.withLocalPart("laci_inverter")),
				new HashSet<URI>(Arrays.asList(Terms.moduleRoles.inverter)));
		 */
		setDefaultNameSpace(document, SBOLTestUtils.example.getNamespaceURI());
		ModuleDefinition laciInverterModuleDef=document.createModuleDefinition("laci_inverter");
		laciInverterModuleDef.addRole(SBOLTestUtils.Terms.moduleRoles.inverter); //TODO: where to add inverter in core2 package so this line of code could be called from?


		ModuleDefinition tetRInverterModuleDef=document.createModuleDefinition("tetr_inverter");
		tetRInverterModuleDef.addRole(SBOLTestUtils.Terms.moduleRoles.inverter);

		SBOLTestUtils.createInverter(document,laciInverterModuleDef,placI,LacI);

		SBOLTestUtils.createInverter(document,tetRInverterModuleDef,ptetR,TetR);

		ModuleDefinition toggleSwitchModuleDef=document.createModuleDefinition("toggle_switch");
		toggleSwitchModuleDef.addRole(SBOLTestUtils.toURI(SBOLTestUtils.example.withLocalPart("module_role/toggle_switch")));

		FunctionalComponent  toggleSwitchModuleDef_TetR=toggleSwitchModuleDef.createFunctionalComponent(
				"TetR", AccessType.PUBLIC, TetR.getIdentity(), DirectionType.INOUT);

		FunctionalComponent  toggleSwitchModuleDef_LacI=toggleSwitchModuleDef.createFunctionalComponent(
				"LacI" ,
				AccessType.PUBLIC,
				LacI.getIdentity(),
				DirectionType.INOUT);


		Module lacInverterSubModule=toggleSwitchModuleDef.createModule(
				"laci_inverter",
				laciInverterModuleDef.getIdentity());

		lacInverterSubModule.createMapsTo(
				"LacI_mapping",
				RefinementType.USEREMOTE,
				laciInverterModuleDef.getFunctionalComponent("TF").getIdentity(),
				toggleSwitchModuleDef_LacI.getIdentity());


		Module tetRInverterSubModule=toggleSwitchModuleDef.createModule(
				"tetr_inverter",
				tetRInverterModuleDef.getIdentity());

		tetRInverterSubModule.createMapsTo(
				"TetR_mapping",
				RefinementType.USEREMOTE,
				tetRInverterModuleDef.getFunctionalComponent("TF").getIdentity(),
				toggleSwitchModuleDef_TetR.getIdentity());

		Model model=document.createModel(
				"toogleswicth",
				URI.create("http://virtualparts.org/part/pIKE_Toggle_1"),
				Model.SBML,
				SystemsBiologyOntology.CONTINUOUS_FRAMEWORK);

		//new HashSet<URI>(Arrays.asList(URI.create("http://sbols.org/v2#module_model")))


		toggleSwitchModuleDef.addModel(model.getIdentity());

		//		SBOLWriter.write(document,(System.out));
		runTest("test/data/ModuleDefinitionOutput.rdf", document, "rdf");
	}

	@Test
	public void test_SBOLDocumentOutput() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		//		SBOLWriter.write(document,(System.out));
		runTest("test/data/SBOLDocumentOutput.rdf", document, "rdf");
	}

	@Test
	public void test_SequenceConstraintOutput() throws Exception
	{
		String prURI="http://partsregistry.org";
		//String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		/*
		Sequence seq=document.createSequence(
				URI.create(prURI + "Part:BBa_J23119:Design"),
				 "ttgacagctagctcagtcctaggtataatgctagc",
				URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
				);
		 */

		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		ComponentDefinition promoter = document.createComponentDefinition(
				"BBa_K174004",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));
		promoter.addRole(SequenceOntology.PROMOTER);

		promoter.setName("pspac promoter");
		promoter.setDescription("LacI repressible promoter");

		ComponentDefinition constPromoter = document.createComponentDefinition(
				"pspac",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));
		constPromoter.addRole(SequenceOntology.PROMOTER);

		promoter.setName("constitutive promoter");
		promoter.setDescription("pspac core promoter region");

		ComponentDefinition operator = document.createComponentDefinition(
				"LacI_operator",
				"",
				new HashSet<URI>(Arrays.asList(ComponentDefinition.DNA)));

		operator.addRole(SequenceOntology.OPERATOR);

		operator.setName("LacI operator");
		operator.setDescription("LacI binding site");

		promoter.createSequenceConstraint(
				"r1",
				RestrictionType.PRECEDES, constPromoter.getIdentity(),operator.getIdentity() );

		//promoter.setSequence(seq.getIdentity());

		//		SBOLWriter.write(document,(System.out));
		runTest("test/data/SequenceConstraintOutput.rdf", document, "rdf");
	}

	@Test
	public void test_SequenceOutput() throws Exception
	{
		String prURI="http://partsregistry.org";

		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		Sequence seq=document.createSequence(
				"BBa_J23119",
				"",
				"ttgacagctagctcagtcctaggtataatgctagc",
				URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
				);
		seq.setWasDerivedFrom(URI.create("http://parts.igem.org/Part:BBa_J23119:Design"));
		//		SBOLWriter.write(document,(System.out));
		runTest("test/data/SequenceOutput.rdf", document, "rdf");
	}

	@Test
	public void test_SimpleComponentDefinitionExample() throws Exception
	{
		String prURI="http://partsregistry.org";


		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		ComponentDefinition promoter = document.createComponentDefinition(
				"BBa_J23119",
				"",
				new HashSet<URI>(Arrays.asList(
						ComponentDefinition.DNA,
						URI.create("http://identifiers.org/chebi/CHEBI:4705")
						)));
		promoter.addRole(SequenceOntology.PROMOTER);
		promoter.addRole(URI.create("http://identifiers.org/so/SO:0000613"));

		promoter.setName("J23119 promoter");
		promoter.setDescription("Constitutive promoter");
		promoter.setWasDerivedFrom(URI.create("http://partsregistry.org/Part:BBa_J23119"));

		document.setDefaultURIprefix(prURI);
		Sequence seq=document.createSequence(
				"BBa_J23119",
				"",
				"ttgacagctagctcagtcctaggtataatgctagc",
				URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html")
				);
		seq.setWasDerivedFrom(URI.create("http://parts.igem.org/Part:BBa_J23119:Design"));
		promoter.addSequence(seq.getIdentity());
		//		SBOLWriter.write(document,(System.out));
		runTest("test/data/SimpleComponentDefinitionExample.rdf", document, "rdf");
	}

	@Test
	public void test_SimpleModuleDefinition() throws Exception
	{
		SBOLDocument document = new SBOLDocument();

		setDefaultNameSpace(document, SBOLTestUtils.example.getNamespaceURI());
		document.setTypesInURIs(true);

		ModuleDefinition module=document.createModuleDefinition("GFP_expression");
		FunctionalComponent  cds=module.createFunctionalComponent(
				"Constitutive_GFP",
				AccessType.PUBLIC,
				URI.create("http://sbolstandard.org/example/GFP_generator"),
				DirectionType.IN);


		FunctionalComponent  protein =module.createFunctionalComponent(
				"GFP_protein",
				AccessType.PUBLIC,
				URI.create("http://sbolstandard.org/example/GFP"),
				DirectionType.OUT);

		module.createInteraction("express_GFP", new HashSet<URI>(Arrays.asList(URI.create("Transcription"))));

		//		SBOLWriter.write(document,(System.out));
		runTest("test/data/SimpleModuleDefinition.rdf", document, "rdf");
	}

	private static void setDefaultNameSpace(SBOLDocument document, String uri)
	{
		if (uri.endsWith("/"))
		{
			uri=uri.substring(0,uri.length()-1);
		}
		document.setDefaultURIprefix(uri);
	}

	@Test
	public void test_BBa_I0462_File() throws Exception
	{
		String fileName = "test/data/SBOL1/BBa_I0462.xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileName, "rdf");
			runTest("test/data/BBa_I0462.rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileName, e);
		}
	}

//	@Test
//	public void test_BBa_T9002_File() throws Exception
//	{
//		String fileName = "test/data/SBOL1/BBa_T9002.xml";
//
//		try
//		{
//			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileName, "rdf");
//			runTest("test/data/BBa_T9002.rdf", actual, "rdf");
//		}
//		catch (SBOLValidationException e)
//		{
//			throw new AssertionError("Failed for " + fileName, e);
//		}
//	}

	@Test
	public void test_labhost_All_File() throws Exception
	{
		String fileName = "test/data/SBOL1/labhost_All.xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileName, "rdf");
			runTest("test/data/labhost_All.rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileName, e);
		}
	}

	@Test
	public void test_labhost_Aspergillus_nidulans() throws Exception
	{
		String filename = "labhost_Aspergillus_nidulans";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Bacillus_subtilis() throws Exception
	{
		String filename = "labhost_Bacillus_subtilis";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Drosophila_melanogaster() throws Exception
	{
		String filename = "labhost_Drosophila_melanogaster";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Escherichia_Coli() throws Exception
	{
		String filename = "labhost_Escherichia_Coli";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Gramnegative_bacteria() throws Exception
	{
		String filename = "labhost_Gram-negative_bacteria";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Insect_Cells() throws Exception
	{
		String filename = "labhost_Insect_Cells";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Kluyveromyces_lactis() throws Exception
	{
		String filename = "labhost_Kluyveromyces_lactis";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Mammalian_Cells() throws Exception
	{
		String filename = "labhost_Mammalian_Cells";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Pichia_pastoris() throws Exception
	{
		String filename = "labhost_Pichia_pastoris";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Plant_Cells() throws Exception
	{
		String filename = "labhost_Plant_Cells";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Saccharomyces_cerevisiae() throws Exception
	{
		String filename = "labhost_Saccharomyces_cerevisiae";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_labhost_Schizosaccharomyces_pombe() throws Exception
	{
		String filename = "labhost_Schizosaccharomyces_pombe";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}



	@Test
	public void test_labhost_Unspecified() throws Exception
	{
		String filename = "labhost_Unspecified";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	//	@Test
	//	public void test_miRNA_sbol() throws Exception
	//	{
	//		//TODO: This file is not parsing for some reason...
	//		String filename = "miRNA_sbol";
	//		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";
	//
	//		try
	//		{
	//			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
	//			runTest("test/data/" + filename + ".rdf", actual, "rdf");
	//		}
	//		catch (SBOLValidationException e)
	//		{
	//			throw new AssertionError("Failed for " + fileDirectory, e);
	//		}
	//	}

	@Test
	public void test_partial_pIKE_left_cassette() throws Exception
	{
		String filename = "partial_pIKE_left_cassette";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_partial_pIKE_right_casette() throws Exception
	{
		String filename = "partial_pIKE_right_casette";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_partial_pIKE_right_cassette() throws Exception
	{
		String filename = "partial_pIKE_right_cassette";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_partial_pTAK_left_cassette() throws Exception
	{
		String filename = "partial_pTAK_left_cassette";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_partial_pTAK_right_cassette() throws Exception
	{
		String filename = "partial_pTAK_right_cassette";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_pIKE_pTAK_cassettes_2() throws Exception
	{
		String filename = "pIKE_pTAK_cassettes 2";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_pIKE_pTAK_cassettes() throws Exception
	{
		String filename = "pIKE_pTAK_cassettes";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_pIKE_pTAK_left_right_cassettes() throws Exception
	{
		String filename = "pIKE_pTAK_left_right_cassettes";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}

	@Test
	public void test_pIKE_pTAK_toggle_switches() throws Exception
	{
		String filename = "pIKE_pTAK_toggle_switches";
		String fileDirectory = "test/data/SBOL1/" + filename + ".xml";

		try
		{
			SBOLDocument actual = SBOLTestUtils.convertSBOL1(fileDirectory, "rdf");
			runTest("test/data/" + filename + ".rdf", actual, "rdf");
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileDirectory, e);
		}
	}


//	@Test
//	public void test_JSONFile() throws Exception
//	{
//		SBOLDocument document = new SBOLDocument();
//		runTest("test/data/emptyJSONFile.json", document, "json");
//
//	}

	@Test
	public void test_memberAnnotations() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		String id    	= "someModel";
		URI source 		= URI.create(id + "_source");
		URI language    = URI.create(id + "_language");
		URI framework   = URI.create(id + "_framework");

		Collection myParts = document.createCollection("myParts", VERSION_1_0);
		myParts.addAnnotation(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"), "turtleString")));

		Model someModel = document.createModel(id, VERSION_1_0, source, language, framework);
		someModel.addAnnotation(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"), "turtleString")));

		ModuleDefinition someModDef = document.createModuleDefinition("someModuleDef", VERSION_1_0);
		someModDef.addAnnotation(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"), "turtleString")));

		Set<URI> interactionType = new HashSet<URI>();
		interactionType.add(URI.create("DNA"));
		Interaction someInteraction = someModDef.createInteraction("someInteraction", interactionType);
		//		someModDef.createFunctionalComponent("someFunctionalComponent", AccessType.PUBLIC, "componentDef", VERSION_1_0, DirectionType.INOUT); //TODO replace componentDef
		//		someInteraction.createParticipation("someParticipation", "someFunctionalComponent");

		Module someModule = someModDef.createModule("someModule", "someModuleDef", VERSION_1_0);
		String someMapsTo_id = "someMapsTo";
		//		MapsTo someMapsTo = someModule.createMapsTo(someMapsTo_id, RefinementType.USELOCAL, "someModule", someMapsTo_id +"_remote");

		String seq_id = "someSeq";
		Sequence someSeq = document.createSequence(seq_id, VERSION_1_0, seq_id + "_element", URI.create("http://encodings.org/encoding"));
		someSeq.addAnnotation(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"), "turtleString")));

		Set<URI> types = new HashSet<URI>();
		types.add(URI.create("someCompDef_type"));
		ComponentDefinition someCompDef = document.createComponentDefinition("someCompDef", VERSION_1_0, types);
		someCompDef.addAnnotation(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"), "turtleString")));

		Component someComponent = someCompDef.createComponent("someComponent", AccessType.PUBLIC, "someCompDef", VERSION_1_0);
		someComponent.addAnnotation(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"), "turtleString")));

		SequenceAnnotation someSequenceAnnotation = someCompDef.createSequenceAnnotation("someSequenceAnnotation", "cut", 1, 10);
		someSequenceAnnotation.addAnnotation(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"), "turtleString")));
		SequenceAnnotation someSequenceAnnotation2 = someCompDef.createSequenceAnnotation("someSequenceAnnotation2", "cut", 1, OrientationType.INLINE);

		//		someSequenceAnnotation.setLocation(); //TODO range, multiRange, cut - how to access?

		SequenceConstraint someSequenceConstraint = someCompDef.createSequenceConstraint("someSequenceConstraint", RestrictionType.PRECEDES, "someComponent", "someComponent");
		someSequenceConstraint.addAnnotation(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"), "turtleString")));

		GenericTopLevel someGenericTopLevel = document.createGenericTopLevel("someGenericTopLevel", VERSION_1_0, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));
		someGenericTopLevel.addAnnotation(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"), "turtleString")));


		runTest("test/data/memberAnnotations.rdf", document, "rdf");
	}


	@Test
	public void test_CreateAndRemoveCollections() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		Collection c = document.createCollection("myParts", VERSION_1_0);
		document.removeCollection(c);

		for(int i = 1; i < 4; i++)
		{
			document.createCollection("myParts" + i, VERSION_1_0);
		}

		document.clearCollections();
		document.createCollection("myParts", VERSION_1_0);
		runTest("test/data/CreateAndRemoveCollections.rdf", document, "rdf");
	}

	@Test
	public void test_CreateAndRemoveComponentDefintion() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		Set<URI> types = new HashSet<URI>();
		types.add((URI.create("someType")));
		ComponentDefinition cd = document.createComponentDefinition("someCompDef", VERSION_1_0, types);
		document.removeComponentDefinition(cd);

		for(int i = 1; i < 4; i++)
		{
			document.createComponentDefinition("someCompDef" + i, VERSION_1_0, types);
		}

		document.clearComponentDefinitions();
		document.createComponentDefinition("someCompDef", VERSION_1_0, types);
		runTest("test/data/CreateAndRemoveComponentDefinition.rdf", document, "rdf");
	}

	@Test
	public void test_CreateAndRemoveModuleDefintion() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		ModuleDefinition md = document.createModuleDefinition("someModDef", VERSION_1_0);
		document.removeModuleDefinition(md);

		for(int i = 1; i < 4; i++)
		{
			document.createModuleDefinition("someModDef"+i, VERSION_1_0);
		}

		document.clearModuleDefinitions();
		document.createModuleDefinition("someModDef", VERSION_1_0);

		runTest("test/data/CreateAndRemoveModuleDefinition.rdf", document, "rdf");
	}

	@Test
	public void test_CreateAndRemoveGenericTopLevel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		GenericTopLevel gen = document.createGenericTopLevel("someGenTopLev", VERSION_1_0, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));
		document.removeGenericTopLevel(gen);

		for(int i = 1; i < 4; i++)
		{
			document.createGenericTopLevel("someGenTopLev"+i, VERSION_1_0, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));
		}

		document.clearGenericTopLevels();
		document.createGenericTopLevel("someGenTopLev", VERSION_1_0, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));

		runTest("test/data/CreateAndRemoveGenericTopLevel.rdf", document, "rdf");
	}

	@Test
	public void test_CreateAndRemoveModel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		Sequence s = document.createSequence("someSequence", VERSION_1_0, "someSeq_element", URI.create("someSeq_encoding"));
		document.removeSequence(s);


		for(int i = 1; i < 4; i++)
		{
			document.createCollection("someSequence" + i, VERSION_1_0);
		}

		document.clearSequences();
		document.createSequence("someSequence", VERSION_1_0, "someSeq_element", URI.create("someSeq_encoding"));
		runTest("test/data/CreateAndRemoveModel.rdf", document, "rdf");
	}

	@Test
	public void test_singleCollection() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		document.createCollection("myParts", VERSION_1_0);
		runTest("test/data/singleCollection.rdf", document, "rdf");
	}


	@Test
	public void test_multipleCollections_no_Members() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		document.createCollection("myPart1", VERSION_1_0);
		document.createCollection("myPart2", VERSION_1_0);
		document.createCollection("myPart3", VERSION_1_0);

		runTest("test/data/multipleCollections_no_Members.rdf", document, "rdf");
	}

	@Test
	public void test_singleGenericTopLevel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		document.createGenericTopLevel("GenericTopLevel", VERSION_1_0, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));
		runTest("test/data/singleGenericTopLevel.rdf", document, "rdf");
	}

	@Test
	public void test_multipleGenericTopLevel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		document.createGenericTopLevel("GenericTopLevel1", VERSION_1_0, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction1", "grn"));
		document.createGenericTopLevel("GenericTopLevel2", VERSION_1_0, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction2", "grn"));
		document.createGenericTopLevel("GenericTopLevel3", VERSION_1_0, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction3", "grn"));

		runTest("test/data/multipleGenericTopLevel.rdf", document, "rdf");
	}

	@Test
	public void test_singleModel() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		String id = "ToggleModel";
		document.createModel( id, VERSION_1_0, URI.create(id + "_source"), URI.create(id + "_language"), URI.create(id + "_framework"));

		runTest("test/data/singleModel.rdf", document, "rdf");
	}


	@Test
	public void test_singleSequence() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		String id = "pLacSeq";
		document.createSequence(id, VERSION_1_0, id + "_elements", URI.create(id + "_encoding"));

		runTest("test/data/singleSequence.rdf", document, "rdf");
	}

	@Test
	public void test_multipleSquences() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		String id = "pLacSeq";
		String id2 = "tetRSeq";
		String id3 = "pLactetRSeq";

		document.createSequence(id, VERSION_1_0, id + "_elements", URI.create(id + "_encoding"));
		document.createSequence(id2, VERSION_1_0, id2 + "_elements", URI.create(id2 + "_encoding"));
		document.createSequence(id3, VERSION_1_0, id3 + "_elements", URI.create(id3 + "_encoding"));

		runTest("test/data/multipleSequences.rdf", document, "rdf");
	}

	@Test
	public void test_single_emptyModuleDefinition() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		Set<URI> roles = SBOLTestUtils.getSetPropertyURI("Inverter");
		ModuleDefinition LacI_Inv = document.createModuleDefinition("LacI_Inv", VERSION_1_0);
		LacI_Inv.setRoles(roles);
		//		LacI_Inv.addRole(URI.create("Inverter"));

		runTest("test/data/singleModuleDefinition.rdf", document, "rdf");
	}

	@Test
	public void test_singleComponentDefinition() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		ComponentDefinition pLac = document.createComponentDefinition("pLac", VERSION_1_0, type);
		pLac.setRoles(role);
		//		pLac.addRole(URI.create("Promoter"));

		runTest("test/data/singleComponentDefinition.rdf", document, "rdf");
	}

	@Test
	public void test_singleCompDef_withSeq() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		Set<URI> type = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Promoter");
		ComponentDefinition pLac = document.createComponentDefinition("pLac", VERSION_1_0, type);
		pLac.setRoles(role);
		//		pLac.setSequence("pLacSeq", VERSION_1_0); //TODO unable to call createSequence for this.

		runTest("test/data/singleCompDef_withSeq.rdf", document, "rdf");
	}

	@Test
	public void test_singleFunctionalComponent() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		document.setComplete(true);
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");

		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		Set<URI> type = SBOLTestUtils.getSetPropertyURI("Protein");
		Set<URI> role = SBOLTestUtils.getSetPropertyURI("Transcriptionfactor");
		ComponentDefinition LacIIn = document.createComponentDefinition("LacIIn", VERSION_1_0, type);
		String compDef_id = LacIIn.getDisplayId();
		LacIIn.createComponent("funcComp", AccessType.PUBLIC, compDef_id, VERSION_1_0);


		runTest("test/data/singleFunctionalComponent.rdf", document, "rdf");
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
	//		Annotation a = new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"),
	//				"TurtleString"));
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
	//		//		myParts.addMember(LacI_Inv_id);
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

	/*
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
						P.getIdentity(), C.getIdentity(), RestrictionType.PRECEDES, null);
		sequenceConstraints.add(struct_constraint);

		return SBOLTestUtils.createComponentDefinition(document, "pLactetR", type, role,
				pLactetRSeq_id, null, sequenceConstraints, subComponents, null);
	}
	 */
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
	//
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
	//		Annotation a = new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"),
	//				"TurtleString"));
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


	public abstract void runTest(final String fileName, final SBOLDocument expected, String fileType)
			throws Exception;

}

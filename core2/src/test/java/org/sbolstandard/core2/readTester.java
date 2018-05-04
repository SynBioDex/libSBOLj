package org.sbolstandard.core2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

class readTester {
	static String filenameXml 	= "writeTesterString_v1.3.xml";
	static String filenameJson   = "writeTesterString_v1.3.json";
	static String filenameTurtle = "writeTesterString_v1.3.ttl";

	static String filenameV1_1 	= "SBOL1/partial_pIKE_left_cassette.xml";
	static String filenameV1_2 	= "SBOL1/partial_pIKE_right_casette.xml";
	static String filenameV1_3 	= "SBOL1/partial_pIKE_right_cassette.xml";
	static String filenameV1_4 	= "SBOL1/partial_pTAK_left_cassette.xml";
	static String filenameV1_5 	= "SBOL1/partial_pTAK_right_cassette.xml";
	static String filenameV1_6 	= "SBOL1/pIKE_pTAK_cassettes 2.xml";
	static String filenameV1_7 	= "SBOL1/pIKE_pTAK_cassettes.xml";
	static String filenameV1_8 	= "SBOL1/pIKE_pTAK_left_right_cassettes.xml";
	static String filenameV1_9 	= "SBOL1/pIKE_pTAK_toggle_switches.xml";
	static String filenameV1_10 	= "SBOL1/miRNA_sbol.xml";
	static String filenameV1_11 	= "SBOL1/labhost_All.xml";
	static String filenameV1_12 	= "SBOL1/BBa_I0462.xml";
	static String filenameV1_13 	= "SBOL1/pACPc_invF.xml";
	static String filenameV1_14 	= "SBOL1/precedesTest.xml";
	static String filenameV1_15 	= "ComponentDefinitionOutput.xml";
	static String filenameV1_16 	= "SimpleComponentDefinitionExample.xml";
	static String filenameV1_17 	= "namespace.xml";
	static String filenameV1_18 	= "SBOL1/SBOL1and2Test.xml";
	static String filenameV1_19 	= "toggle.xml";


	static String path = "/Users/myers/git/libSBOLj/core2/src/test/resources/";

	public static void main(String[] args) throws SBOLValidationException, IOException, SBOLConversionException, SynBioHubException {
	
		SBOLReader.read("/Users/myers/Downloads/empty.xml");
//		File file_base = null;
//		file_base = new File("/Users/myers/Downloads/cello_sbol/");
//		for (File f : file_base.listFiles()) {
//			SBOLDocument doc = SBOLReader.read(f.getAbsoluteFile());
//			for (ModuleDefinition md : doc.getModuleDefinitions()) {
//				if (md.getDisplayId().contains("_circuit")) {
//					String id;
//					if (md.getDisplayId().contains("_alt_")) {
//						id = md.getDisplayId().substring(0,md.getDisplayId().indexOf("_alt_",8)+4);
//					} else {
//						id = md.getDisplayId().substring(0,md.getDisplayId().indexOf("_",8));
//					}
//					md = (ModuleDefinition)doc.rename(md, id);
//					for (Module mod : md.getModules()) {
//						String original = mod.getIdentity().toString().substring(mod.getIdentity().toString().lastIndexOf("/")+1);
//						String swap = original.substring(original.lastIndexOf("_circuit_")+1);
//						mod.setIdentity(URI.create(mod.getIdentity().toString().replace(original,swap)));
//						mod.setPersistentIdentity(mod.getIdentity());
//						mod.setDisplayId(swap);
//						//System.out.println("module original:"+original);
//						//System.out.println("module swap:"+swap);
//						for (MapsTo mapsTo : mod.getMapsTos()) {
//							String originalMap = mapsTo.getIdentity().toString().substring(mapsTo.getIdentity().toString().lastIndexOf("/")+1);
//							String swapMap = originalMap.substring(originalMap.lastIndexOf("_protein_")+9);
//							//System.out.println("mapsTo original:"+originalMap);
//							//System.out.println("mapsTo swap:"+swapMap);
//							String newIdentity = mapsTo.getIdentity().toString().replace(originalMap,swapMap);
//							newIdentity = newIdentity.replace(original,swap);
//							//System.out.println("Old:"+mapsTo.getIdentity());
//							mapsTo.setIdentity(URI.create(newIdentity));
//							mapsTo.setPersistentIdentity(mapsTo.getIdentity());
//							mapsTo.setDisplayId(swapMap);
//							//System.out.println("New:"+mapsTo.getIdentity());
//						}
//					}
//				}
//			}
//			doc.write("/Users/myers/Downloads/cello_sbol2/"+f.getName());
//		}
		
		
//		SynBioHubFrontend sbh = new SynBioHubFrontend("https://synbiohub.org");
//		sbh.login("jakebeal@gmail.com", "facets");
//		SBOLDocument doc = sbh.getSBOL(URI.create("https://synbiohub.org/user/FACETS/Reporters/foo_md/1"));
//		doc.write(System.out);
		//SBOLDocument doc = SBOLReader.read("/Users/myers/Downloads/doc.xml");
		//doc = doc.createRecursiveCopy(doc.getTopLevel(URI.create("http://michael.zhang/Eukaryotic_Promoters_GeneratedInstance6/1")));
		//doc.write(System.out);
//		try {
//			System.out.println(SynBioHubFrontend.getRegistries());
//		}
//		catch (SynBioHubException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		try {
//			SBOLDocument doc = new SBOLDocument();
//			doc.read("/Users/myers/Downloads/test.xml");
//			doc.addRegistry("https://synbiohub.org");
//			Activity activity = doc.getActivity(URI.create("http://michael.zhang/UnnamedPart_SBOLDesignerActivity/1"));
//			for (Association association : activity.getAssociations()) {
//				System.out.println(association.getAgentURI());
//				System.out.println(association.getAgent());
//			}
//			doc.setDefaultURIprefix("http://dummy.org");
//			ComponentDefinition cd = doc.createComponentDefinition("CD1", ComponentDefinition.DNA);
//			ComponentDefinition variant = doc.createComponentDefinition("Variant", ComponentDefinition.DNA);
//			ComponentDefinition variant2 = doc.createComponentDefinition("Variant2", ComponentDefinition.DNA);
//			doc.createComponentDefinition("dummyCD", ComponentDefinition.DNA);
//			Component comp = cd.createComponent("comp", AccessType.PUBLIC, "dummyCD");
//			Component comp2 = cd.createComponent("comp2", AccessType.PUBLIC, "dummyCD");
//			CombinatorialDerivation combDeriv = doc.createCombinatorialDerivation("testCombo", cd.getIdentity());
//			VariableComponent vc = combDeriv.createVariableComponent("vc", OperatorType.ONE, comp);
//			vc.addVariant(variant.getIdentity());
//			VariableComponent vc2 = combDeriv.createVariableComponent("vc2", OperatorType.ONE, comp2);
//			vc2.addVariant(variant2.getIdentity());
//			SBOLDocument doc2 = doc.createRecursiveCopy(combDeriv);
//			//doc.write("/Users/myers/combo.xml");
//			//SBOLDocument doc2 = SBOLReader.read("/Users/myers/combo.xml");
//			doc2.write(System.out);
//			SBOLValidate.compareDocuments("doc", doc, "doc2", doc2);
//			if (SBOLValidate.getNumErrors() > 0) {
//				for (String error : SBOLValidate.getErrors()) {
//					System.out.println(error);
//				}	
//			}
//			SynBioHubFrontend sbh = new SynBioHubFrontend("http://localhost:7777","https://synbiohub.org");
//			sbh.login("myers@ece.utah.edu", "test");
//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//			String filename = sbh.getAttachment(URI.create("https://synbiohub.org/user/myers/testSBML/attachment_00009S03IDsWYRzOri742C/1"), outputStream);
//			File file = new File("/Users/myers/"+filename);
//			FileOutputStream fileOutputStream = new FileOutputStream(file);
//			fileOutputStream.write(outputStream.toByteArray());
//			fileOutputStream.close();
//			sbh.createCollection("testCDCol", "1", "testName", "testDescription", "", true,"/Users/myers/Downloads/crispr_archive.omex");
			//sbh.addToCollection(URI.create("https://synbiohub.org/user/myers/testCDCol/testCDCol_collection/1"), false, "/Users/myers/gb.zip");
			//sbh.attachFile(URI.create("https://synbiohub.org/user/myers/Tester_1/BBa_J61101/1"), "/Users/myers/Downloads/pc_logo_400.png");
//			sbh.getAttachment(URI.create("https://synbiohub.org/user/myers/Tester_1/attachment_00009Rh5j0pvezwyVBNeU4/1"),"/Users/myers/");
//			SBOLReader.setURIPrefix("http://dummy.org/");
//			SBOLReader.setCompliant(true);
//			long startTime = System.nanoTime();
//			SBOLDocument doc = SBOLReader.read("/Users/myers/Documents/Projects/acs_2017_vpr/acs_2017_vpr.sbol");
//			long endTime = System.nanoTime();
//			System.out.println("Read time = "+ (endTime - startTime)); 
			//SBOLDocument doc2 = new SBOLDocument();
			//doc.setDefaultURIprefix("http://dummy.org/");
			//doc2.createCopy(doc);
			//doc2.write(System.out);
			//doc = doc.changeURIPrefixVersion("http://dummy.org/", "1");
			//doc.write(System.out);
//			SBOLDocument doc = SBOLReader.read("/Users/myers/git/SBOLTestRunner/SB_Tester/Emulated/toggle.xml_Emulated.xml");
//			SBOLDocument doc2 = SBOLReader.read("/Users/myers/git/SBOLTestRunner/SB_Tester/Retrieved/toggle.xml_Retrieved.xml");
//			SBOLValidate.compareDocuments("em", doc, "re", doc2);
//			if (SBOLValidate.getNumErrors() > 0) {
//				for (String error : SBOLValidate.getErrors()) {
//					System.out.println(error);
//				}	
//			}
			//SBOLDocument doc = SBOLReader.read("/Users/myers/Downloads/bug.xml");
			//doc = doc.changeURIPrefixVersion("http://new.org/", "1.0");
			//doc.write(System.out);
			//String result = sbh.sparqlQuery("select ?s ?p ?o where { ?s ?p ?o . FILTER(?s = <https://synbiohub.org/public/igem/BBa_B0015/1>) }");
			//System.out.println(result);
//			SBOLDocument doc = sbh.getSBOL(URI.create("https://synbiohub.org/public/igem/BBa_J23070/1"));
//			doc.write(System.out);
//			System.out.println(sbh.getRootCollectionMetadata());
//			sbh.login("myers@ece.utah.edu", "test2");
//			System.out.println(sbh.getRootCollectionMetadata());
//			System.out.println(sbh.getSubCollectionMetadata(URI.create("https://synbiohub.org/user/myers/NewTest/NewTest_collection/1")));
//			HashSet<URI> myset = new HashSet<URI>();
//			myset.add(URI.create("https://synbiohub.org/user/myers/test/test_collection/1"));
//			System.out.println(sbh.getMatchingComponentDefinitionMetadata(null, null, null, null, 0, 50));
//			SBOLReader.setURIPrefix("http://www.async.ece.utah.edu/");
//			doc = SBOLReader.read("/Users/myers/Downloads/QUAS_Promoter_Test.gb");
//			doc.write(System.out);
//			SBOLReader.setURIPrefix("http://dummy.org/");
//			SBOLReader.setVersion("1");
//			//SBOLDocument doc3 = SBOLReader.read("/Users/myers/Downloads/GenBankEx/sequence1.gb");
//			SBOLDocument doc3 = SBOLReader.read("/Users/myers/Downloads/test.xml");
//			doc3 = doc3.changeURIPrefixVersion("https://synbiohub.org/public/", "1");
//			doc3.write(System.out);
//			//ModuleDefinition md = doc3.getModuleDefinition(URI.create("http://sbols.org/CRISPR_Example/CRPb_characterization_Circuit/1.0"));
//			//System.out.println(md.getWasDerivedFrom());
//			//SBOLValidate.validate("/Users/myers/Downloads/GTTest.xml", "http://www.async.ece.utah.edu", true, true, true,
//			//		false, "1", true, "", "compare", "main", "" ,  false, false, false, "/Users/myers/Downloads/GTTest1.xml", false, false);
//			//SBOLValidate.validate("/Users/myers/Downloads/GTTest1.xml", "http://www.async.ece.utah.edu", true, true, true,
//			//		false, "2", true, "", "compare", "main", "",  false, false, false, "/Users/myers/Downloads/GTTest2.xml", false, false);
//			//SBOLValidate.validate("/Users/myers/Downloads/GTTest2.xml", "http://www.async.ece.utah.edu", true, true, true,
//			//		false, "3", true, "", "compare", "main", "",  false, false, false, "/Users/myers/Downloads/GTTest3.xml", false, false);
//			//SBOLReader.setURIPrefix("http://dummy.org/test/");
//			//SBOLDocument doc = SBOLReader.read(path +"GenBank/sequence1.gb");
//			//doc.setDefaultURIprefix("http://dummy.org/");
//			//doc.createSequence("foo", "1", "AGCT", Sequence.IUPAC_DNA);
//			//doc.createSequence("test_U49845_seq", "1", "AGCT", Sequence.IUPAC_DNA);
//			//System.out.println("BEFORE");
//			//doc.write(System.out);
////			SBOLDocument doc = SBOLReader.read("/Users/myers/Downloads/GenTog.xml");
////			for (TopLevel topLevel : doc.getTopLevels()) {
////				if (topLevel.getIdentity().toString().startsWith("https://synbiohub.org")) {
////					doc.removeTopLevel(topLevel);
////				}
////			}
////			SBOLDocument doc2 = doc.changeURIPrefixVersion("http://newUri.org", "2.0");
////			SBOLValidate.validateSBOL(doc2, true, true, true);	
////			if (SBOLValidate.getNumErrors() > 0) {
////				for (String error : SBOLValidate.getErrors()) {
////					System.out.println(error+"\n");
////				}
////			}
//			//doc2.write(System.out);
////						
////			SBOLDocument doc2 = new SBOLDocument();
////			doc2.setDefaultURIprefix("http://dummy.org");
////			doc2.setComplete(true);
////			ComponentDefinition cd = doc2.createComponentDefinition("testCD", "1", ComponentDefinition.DNA);
////			//cd.addSequence(URI.create("http://myfakesequence.com"));
////			
////			SynBioHubFrontend sfe = doc2.addRegistry("https://synbiohub.cidarlab.org","http://synbiohub.cidarlab.org");
////			HashSet<URI> collections = new HashSet<URI>();
////			collections.add(URI.create("http://synbiohub.cidarlab.org/public/cidar_ice/cidarlab_ice_folder_5/current"));
////			System.out.println(sfe.getMatchingComponentDefinitionMetadata(null, null, null, collections, 0, 10));
////			HashSet<URI> types = new HashSet<URI>();
////			types.add(URI.create("http://www.biopax.org/release/biopax-level3.owl#DnaRegion"));
////			HashSet<URI> roles = new HashSet<URI>();
////			roles.add(URI.create("http://identifiers.org/so/SO:0000167"));
////			HashSet<URI> collections = new HashSet<URI>();
////			collections.add(URI.create("https://synbiohub.org/public/igem/category/regulation/constitutive_collection/1"));
////			System.out.println(sfe.getMatchingComponentDefinitionMetadata("", roles, types, collections, 1, 10));
//			
//			//doc2.read("/Users/myers/Downloads/MyPartTest.xml");
//			//sfe.removeRegistryParts(doc2);
//			//doc2.write(System.out);
//			
//			
//			//System.out.println(sfe.getCountTopLevels("Collection"));
//			//doc2.getComponentDefinition(URI.create("https://synbiohub.org/public/igem/BBa_J18935/1"));
//			//SBOLWriter.write(doc2, System.out);
//			sbh.login("myers@ece.utah.edu", "test");
//			sbh.submit("testCDCol", "1", "testName", "testDescription", "", "http://dummy.org/foo/foo_collection,http://dummy.org/foo2/foo2_collection", "1", null);
//			//ArrayList<IdentifiedMetadata> imd = doc2.getRegistry("https://synbiohub.org:9090").searchRootCollectionMetadata();
//			//System.out.println(imd.toString());
//			//doc2.getTopLevel(new URI("https://synbiohub.org/public/igem/BBa_K136042/1"));
//			//doc2.write(System.out);
//			
////			InputStream file = readTester.class.getResourceAsStream(path +filenameV1_19);
////			if (file == null)
////				file = readTester.class.getResourceAsStream("/" + path + filenameV1_19);
//
//			//			InputStream file = readTester.class.getResourceAsStream(path + filenameV1_1);
//			//SBOLReader.setURIPrefix("http://www.async.ece.utah.edu");
//			//SBOLReader.setVersion("1.0");
//			//SBOLReader.setTypesInURI(true);
////			SBOLDocument document1 = SBOLReader.read("/Users/myers/Downloads/michael.xml");
////			for (ComponentDefinition componentDefinition : document1.getComponentDefinitions()) {
////				if (componentDefinition.getComponents().size()==0) continue;
////				//String original = componentDefinition.getSequenceByEncoding(Sequence.IUPAC_DNA).getElements();
////				//String implied = componentDefinition.getImpliedNucleicAcidSequence();
////				System.out.println(componentDefinition.getIdentity());
////				System.out.println("Implied: "+componentDefinition.getImpliedNucleicAcidSequence());
////			}
//			
//			//SBOLDocument document2 = document1.createRecursiveCopy(
//			//		document1.getTopLevel(URI.create("http://sbolhub.org/col/james_test_sbol2_061015155208")));
//			//ByteArrayOutputStream out = new ByteArrayOutputStream();
//			//SBOLWriter.write(document1, out);//, SBOLReader.xmlV1);
//			//document1 = SBOLReader.read(new ByteArrayInputStream(out.toByteArray()));
//			//SBOLWriter.write(document2,(System.out));
//			//ByteArrayOutputStream out = new ByteArrayOutputStream();
//			//SBOLWriter.writeV1(document1, out);
//			//SBOLDocument document2 = SBOLReader.read(new ByteArrayInputStream(out.toByteArray()));
//			//SBOLWriter.writeV1(document2, (System.out));
//			
//			//			file = readTester.class.getResourceAsStream(path +filenameV1_14);
//			//			if (file == null)
//			//				file = readTester.class.getResourceAsStream("/" + path + filenameV1_14);
//			//			document1.read(file);
//			//			SBOLDocument document2 = new SBOLDocument();
//			//			for (ComponentDefinition cd : document1.getComponentDefinitions()) {
//			//				ComponentDefinition cd2 = (ComponentDefinition) document2.createCopy(cd);
//			//				if (!cd.equals(cd2))
//			//					System.out.println(cd.getIdentity() + " " + cd.equals(cd2));
//			//			}
//			//			for (Sequence s : document1.getSequences()) {
//			//				Sequence s2 = (Sequence) document2.createCopy(s);
//			//				if (!s.equals(s2))
//			//					System.out.println(s.getIdentity() + " " + s.equals(s2));
//			//			}
//			//document1.setDefaultURIprefix("http://www.some.org");
//			//document1.createCollection("abc", "1.0");
//			//document1 = SBOLTestUtils.writeAndRead(document1);
//			//			SBOLDocument document  = SBOLReader.read(filenameRdf);
//			//			SBOLDocument document1 = SBOLReader.readRdf(filenameV1_8);
//			//			SBOLDocument document2 = SBOLReader.readJson(filenameJson);
//			//			SBOLDocument document3 = SBOLReader.readTurtle(filenameTurtle);
//
//			//SBOLDocument doc = SBOLReader.read("/Users/myers/downloads/test.xml");
//			//doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
//			//ComponentDefinition cd = doc.getComponentDefinition("pTAK_Toggle_10","");
//			//GenBank.write(cd, (System.out));
////			SBOLFactory.createSequence("http://www.abc.com", "foo", "1.0", "AGCT", Sequence.IUPAC_DNA);
////			//SBOLFactory.setSBOLDocument(new SBOLDocument());
////			SBOLFactory.createSequence("http://www.abc.com", "foo2", "1.0", "AGCT", Sequence.IUPAC_DNA);
////			SBOLFactory.write((System.out));
////			
////			GenBank.setURIPrefix("http://www.async.ece.utah.edu");
////			SBOLDocument doc = GenBank.read("/Users/myers/downloads/GenBankEx/sequence1.gb"/*pTACK_Toggle_Switch_9*/);
////			doc.write("/Users/myers/downloads/GenBankEx/sequence1.xml");
////			doc = SBOLReader.read("/Users/myers/downloads/GenBankEx/sequence1.xml");
////			doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
////			for (ComponentDefinition componentDefinition : doc.getRootComponentDefinitions()) {
////				ComponentDefinition cd = doc.getComponentDefinition(componentDefinition.getIdentity());
////				GenBank.write(cd, "/Users/myers/downloads/GenBankEx/sequence1out.gb");		
////			}
////			
////			doc = GenBank.read("/Users/myers/downloads/GenBankEx/sequence2.gb");
////			doc.write("/Users/myers/downloads/GenBankEx/sequence2.xml");
////			doc = SBOLReader.read("/Users/myers/downloads/GenBankEx/sequence2.xml");
////			doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
////			for (ComponentDefinition componentDefinition : doc.getRootComponentDefinitions()) {
////				ComponentDefinition cd = doc.getComponentDefinition(componentDefinition.getIdentity());
////				GenBank.write(cd, "/Users/myers/downloads/GenBankEx/sequence2out.gb");		
////			}
////			
////			doc = GenBank.read("/Users/myers/downloads/GenBankEx/sequence3.gb");
////			doc.write("/Users/myers/downloads/GenBankEx/sequence3.xml");
////			doc = SBOLReader.read("/Users/myers/downloads/GenBankEx/sequence3.xml");
////			doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
////			for (ComponentDefinition componentDefinition : doc.getRootComponentDefinitions()) {
////				ComponentDefinition cd = doc.getComponentDefinition(componentDefinition.getIdentity());
////				GenBank.write(cd, "/Users/myers/downloads/GenBankEx/sequence3out.gb");		
////			}
////			
////			SBOLDocument doc = GenBank.read("/Users/myers/git/libSBOLj/core2/src/test/resources/test/data/GenBank/sequence4.gb");
////			doc.write(System.out);
////			//doc.write("/Users/myers/downloads/GenBankEx/sequence4.xml");
////			//doc = SBOLReader.read("/Users/myers/downloads/GenBankEx/sequence4.xml");
////			doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
////			for (ComponentDefinition componentDefinition : doc.getRootComponentDefinitions()) {
////				ComponentDefinition cd = doc.getComponentDefinition(componentDefinition.getIdentity());
////				GenBank.write(cd, System.out);	
////			}
//			//SBOLWriter.write(document1,(System.out));
//			//SBOLWriter.writeRDF(SBOLTestUtils.writeAndRead(document1),(System.out));
//
//			//			SBOLWriter.writeJson(document2,(System.out));
//			//			SBOLWriter.writeTurtle(document3,(System.out));
//
//			//			URI identity = URI.create("http://www.async.ece.utah.edu/pLactetRSeq/2/0");
//			//			System.out.println(SBOLReader.getParentURI(identity));

//		} catch (Throwable e) {
//			e.printStackTrace();
//		}

	}
}

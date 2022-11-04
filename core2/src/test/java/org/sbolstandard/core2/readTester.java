package org.sbolstandard.core2;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.synbiohub.frontend.IdentifiedMetadata;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

class readTester {

	static String path = "/Users/myers/git/libSBOLj/core2/src/test/resources/";

	public static void main(String[] args) throws SynBioHubException, SBOLValidationException, IOException, SBOLConversionException {

		//SBOLReader.setURIPrefix("http://dummy.org");
		SBOLDocument doc = SBOLReader.read("/Users/myers/Downloads/uri-iri/uri-iri.rdf");
//		SynBioHubFrontend sbh2 = new SynBioHubFrontend("https://hub-staging.sd2e.org","https://hub.sd2e.org");
//		sbh2.login("myers@ece.utah.edu", "MaWen69!");
//		sbh2.createCollection("test", "1", "test", "test", "", true);
//		SBOLDocument doc = SBOLReader.read("/Users/myers/Downloads/NOR.xml");
//		sbh2.addToCollection(URI.create("https://hub.sd2e.org/user/cmyers_admin/test/test_collection/1"), true, doc);
//		SBOLDocument doc = SBOLReader.read("/Users/myers/git/sbolgraphExample/circuit.xml");
		doc = doc.changeURIPrefixVersion("http://dummy.org/", "1", "1");
		doc.write(System.out);
//		doc.write(System.out);
//		SBOLReader.setURIPrefix("http://dummy.org");
//		SBOLDocument doc = SBOLReader.read("/Users/myers//Downloads/Output/BBa_K1132032.gb");
//		SBOLDocument doc2 = SnapGene.detectFeatures("tccctatcagtgatagagattgacatccctatcagtgatagagatactgagcactactagagaaagagga\n" + 
//				"gaaatactagatgaaaaacataaatgccgacgacacatacagaataattaataaaattaaagcttgtaga\n" + 
//				"agcaataatgatattaatcaatgcttatctgatatgactaaaatggtacattgtgaatattatttactcg\n" + 
//				"cgatcatttatcctcattctatggttaaatctgatatttcaatcctagataattaccctaaaaaatggag\n" + 
//				"gcaatattatgatgacgctaatttaataaaatatgatcctatagtagattattctaactccaatcattca\n" + 
//				"ccaattaattggaatatatttgaaaacaatgctgtaaataaaaaatctccaaatgtaattaaagaagcga\n" + 
//				"aaacatcaggtcttatcactgggtttagtttccctattcatacggctaacaatggcttcggaatgcttag\n" + 
//				"ttttgcacattcagaaaaagacaactatatagatagtttatttttacatgcgtgtatgaacataccatta\n" + 
//				"attgttccttctctagttgataattatcgaaaaataaatatagcaaataataaatcaaacaacgatttaa\n" + 
//				"ccaaaagagaaaaagaatgtttagcgtgggcatgcgaaggaaaaagctcttgggatatttcaaaaatatt\n" + 
//				"aggttgcagtgagcgtactgtcactttccatttaaccaatgcgcaaatgaaactcaatacaacaaaccgc\n" + 
//				"tgccaaagtatttctaaagcaattttaacaggagcaattgattgcccatactttaaaaattaataacact\n" + 
//				"gatagtgctagtgtagatcactactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgg\n" + 
//				"gcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtg\n" + 
//				"ggcctttctgcgtttatatactagagacctgtaggatcgtacaggtttacgcaagaaaatggtttgttat\n" + 
//				"agtcgaataaa", "http://dummy.org", "BBa_F2620", "1", "/Users/myers/Downloads/BBa_F2620.png", "BBa_F2620");
//		doc2.write(System.out);
//		SynBioHubFrontend sbh2 = new SynBioHubFrontend("http://localhost:7777","https://synbiohub.org");
//		sbh2.login("myers", "test");
//		String result = sbh2.sparqlAdminQuery("PREFIX sbol: <http://sbols.org/v2#>\n" + 
//				"select ?s \n" + 
//				"FROM <https://synbiohub.org/user/myers>\n" + 
//				"where {\n" + 
//				"  ?s sbol:template <https://synbiohub.org/user/myers/MyDerivTest/MyDerivTest/1>\n" + 
//				"     }");
//		System.out.println(result);
//		sbh2.updateCollectionIcon(URI.create("https://synbiohub.org/public/Terminators/Terminators_collection/1"), "/Users/myers/Downloads/sbol_logo.png");
		//sbh	2.updateUser(16, "test", "testName", "test@email.com", "testAffiliation", false, false, false);
//		sbh2.deleteUser(16);
		//JSONObject logs = sbh2.getUserProfile();
		//System.out.println(logs.toString());
		//sbh2.setNewPassword("4918a7b50bceaf3798763444159ba9bce04ca0b3","test2");
//
//		SBOLDocument docS = new SBOLDocument();
//		docS.setDefaultURIprefix("http://www.dummy.org");
//		ComponentDefinition cd2 = docS.createComponentDefinition("foo", "1", ComponentDefinition.DNA_REGION);
//		sbh2.createCollection("pySBOL_test", "1", "testAPI", "pySBOL test", 
//				"A temporary collection used for running pySBOL integration tests", true);
//		cd2.addRole(SequenceOntology.PROMOTER); 
//		sbh2.addToCollection(URI.create("https://synbiohub.org/user/myers/pySBOL_test/pySBOL_test_collection/1"), true, docS);
//		cd2.clearRoles();
//		cd2.addRole(SequenceOntology.CDS); 
//		System.out.println(cd2.getRoles());
//		sbh2.addToCollection(URI.create("https://synbiohub.org/user/myers/pySBOL_test/pySBOL_test_collection/1"), true, docS);
//		System.out.println("SUCCESS 0");

//		if (true) return; 		
////		SBOLDocument doc2 = new SBOLDocument();
////		ComponentDefinition wt = doc2.createComponentDefinition("wildType", ComponentDefinition.DNA_REGION);
////		ComponentDefinition insert = doc2.createComponentDefinition("insert", ComponentDefinition.DNA_REGION);
//		
//		SBOLReader.setURIPrefix("http://dummy.org");
//		SBOLDocument doc = SBOLReader.read("/Users/myers/Downloads/w303.gff");
//		ModuleDefinition md = doc.createModuleDefinition("w303", "1");
//		md.addRole(URI.create("http://purl.obolibrary.org/obo/NCIT_C14419"));
//		for (ComponentDefinition cd : doc.getRootComponentDefinitions()) {
//			md.createFunctionalComponent(cd.getDisplayId()+"_fc", AccessType.PUBLIC, cd.getIdentity(), DirectionType.NONE);
//		}
//		doc.write("/Users/myers/Downloads/w303_merge.xml");
//		doc.write("/Users/myers/Downloads/w303_merge.gff",SBOLDocument.GFF3format);
//
//		SequenceOntology so = new SequenceOntology();
//		doc = SBOLReader.read("/Users/myers/Downloads/pCBV38_converted_for_SBOL.sbol");
//		doc.setDefaultURIprefix("http://www.dummy.org");
//		for (ComponentDefinition cd : doc.getComponentDefinitions()) {
//			String seq = cd.getSequenceByEncoding(Sequence.IUPAC_DNA).getElements();
//			cd.addType(SequenceOntology.CIRCULAR);
//			for (SequenceAnnotation sa : cd.getSequenceAnnotations()) {
//				ComponentDefinition newCD = doc.createComponentDefinition(sa.getDisplayId(), sa.getVersion(), ComponentDefinition.DNA_REGION);
//				if (sa.containsRole(so.getURIbyName("region"))) {
//					if (sa.getDisplayId().equals("annotation7")) {
//						newCD.addRole(so.getURIbyName("tag"));
//					} else if (sa.getDisplayId().equals("annotation9")) {
//						newCD.addRole(so.getURIbyName("terminator"));
//					} else if (sa.getDisplayId().equals("annotation1")) {
//						newCD.addRole(so.getURIbyName("ribozyme"));
//					} else if (sa.getDisplayId().equals("annotation3")) {
//						newCD.addRole(so.getURIbyName("binding_site"));
//					} else if (sa.getDisplayId().equals("annotation5")) {
//						newCD.addRole(so.getURIbyName("promoter"));
//					} else if (sa.getDisplayId().equals("annotation10")) {
//						newCD.addRole(so.getURIbyName("five_prime_UTR"));
//					} else if (sa.getDisplayId().equals("annotation8")) {
//						newCD.addRole(so.getURIbyName("terminator"));
//					} else if (sa.getDisplayId().equals("annotation4")) {
//						newCD.addRole(so.getURIbyName("ribosome_entry_site"));
//					} else {
//						System.out.println(sa.getDisplayId());
//					}
//				} else {
//					newCD.setRoles(sa.getRoles());
//				}
//				sa.clearRoles();
//				Component comp = cd.createComponent(sa.getDisplayId()+"_comp", AccessType.PUBLIC, newCD.getIdentity());
//				if (sa.isSetName()) {
//					newCD.setName(sa.getName());
//				}
//				sa.setComponent(comp.getIdentity());
//				String elements = "";
//				for (Location loc : sa.getSortedLocations()) {
//					if (loc instanceof Range) {
//						Range range = (Range)loc;
//						if (loc.getOrientation().equals(OrientationType.REVERSECOMPLEMENT)) {
//							// TODO: is this correct?
//							elements += Sequence.reverseComplement(
//									seq.substring(range.getStart()-1, range.getEnd()),
//									ComponentDefinition.DNA_REGION);
//						} else {
//							elements += seq.substring(range.getStart()-1, range.getEnd());
//						}
//					}
//				}
//				Sequence newSeq = doc.createSequence(newCD.getDisplayId()+"_seq", 
//						newCD.getVersion(), elements, Sequence.IUPAC_DNA);
//				newCD.addSequence(newSeq);
//			}
//		}
//		doc.write("/Users/myers/Downloads/pCBV38_converted_for_SBOL2.sbol");
		
//		
//		docS = new SBOLDocument();
//		docS.setDefaultURIprefix("http://www.dummy.org");
//		cd = docS.createComponentDefinition("bar", "1", ComponentDefinition.DNA_REGION);
//		cd.addRole(SequenceOntology.PROMOTER);
//		sbh2.createCollection("pySBOL_test", "1", "pySBOL test", 
//				"A temporary collection used for running pySBOL integration tests", "",
//				true, docS);
//		System.out.println("SUCCESS 1");
//		
//		docS = new SBOLDocument();
//		docS.setDefaultURIprefix("http://www.dummy.org");
//		cd = docS.createComponentDefinition("bar", "1", ComponentDefinition.DNA_REGION);
//		cd.addRole(SequenceOntology.CDS);
//		try {
//			sbh2.addToCollection(URI.create("https://synbiohub.org/user/myers/pySBOL_test/pySBOL_test_collection/1"), false, docS);
//			System.out.println("FAIL 2");
//		} catch (Exception e) {
//			System.out.println("SUCCESS 2");
//		}
//		
//		docS = new SBOLDocument();
//		docS.setDefaultURIprefix("http://www.dummy.org");
//		cd = docS.createComponentDefinition("bar", "1", ComponentDefinition.DNA_REGION);
//		cd.addRole(SequenceOntology.CDS);
//		sbh2.addToCollection(URI.create("https://synbiohub.org/user/myers/pySBOL_test/pySBOL_test_collection/1"), true, docS);
//		System.out.println("SUCCESS 3");

//		SynBioHubFrontend sbh = new SynBioHubFrontend("https://hub.sd2e.org");
//		sbh.login("login", "password");
//		File dir = new File("/Users/myers/Downloads/GFF_v3_34_strains/");
//		File[] directoryListing = dir.listFiles();
//		SBOLDocument collDoc = new SBOLDocument();
//		collDoc.setDefaultURIprefix("http://sd2e.org");
//		Collection collection = collDoc.createCollection("novel_chassis_strains","1");
//		collection.setDescription("This is a collection of strains for the Novel Chassis challenge problem of SD2.");
//		File collFile = new File("/Users/myers/Downloads/GFF_v3_34_strains_SBOL/novel_chassis_strain_collection.xml");
//		URI collectionUri = URI.create("https://hub.sd2e.org/user/sd2e/design/design_collection/1");
//		if (directoryListing != null) {
//			for (File child : directoryListing) {
//				System.out.println("Reading: " + child.getAbsolutePath());
//				String fastaFile = child.getAbsolutePath().replace("/GFF_v3_34_strains/", "/Fasta_34_strains/").replace(".gff", ".fa");
//				String sbolFile = child.getAbsolutePath().replace("/GFF_v3_34_strains/", "/GFF_v3_34_strains_SBOL/").replace(".gff", ".xml");
//				System.out.println("Reading: " + fastaFile);
//				String id = child.getName().replace(".gff","");
//				System.out.println("Reading: " + id);
//				URI mdUri = URI.create("https://hub.sd2e.org/user/sd2e/design/"+id+"/1");
//				SBOLDocument sbhDoc = sbh.getSBOL(mdUri,false);
//				ModuleDefinition md = sbhDoc.getModuleDefinition(URI.create("https://hub.sd2e.org/user/sd2e/design/"+id+"/1"));
//				try {
//					Annotation anno = md.getAnnotation(new QName("http://sd2e.org#","stub_object"));
//					md.removeAnnotation(anno);
//					SBOLDocument doc = SBOLReader.read(child);
//					ComponentDefinition cd = doc.getComponentDefinition(id,"");
//					if (cd == null) {
//						System.out.println("CANNOT FIND: " + id);
//					}
//					cd = (ComponentDefinition)doc.rename(cd, cd.getDisplayId()+"_genome");
//					SBOLReader.setDisplayId(id+"_seq");
//					doc.read(fastaFile);
//					SBOLReader.setDisplayId(null);
//					cd.addSequence(id+"_seq");
//					FunctionalComponent fc = md.getFunctionalComponent(cd.getDisplayId());
//					md.removeFunctionalComponent(fc);
//					ModuleDefinition newMD = (ModuleDefinition)doc.createCopy(md,"http://sd2e.org",md.getDisplayId(),md.getVersion());
//					newMD.createFunctionalComponent(cd.getDisplayId(), AccessType.PUBLIC, cd.getIdentity(), DirectionType.NONE);
//					collection.addMember(mdUri);
//					doc.write(sbolFile);
//					sbh.addToCollection(collectionUri, true, doc);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				//System.out.println("Writing: " + child.getAbsolutePath().replace(".gff", ".xml"));
//				//doc.write(System.out,SBOLDocument.GFF3format);
//				//doc.write(child.getAbsolutePath().replace(".gff", ".xml"));
//		    }
//		} 
//		collDoc.write(collFile);
//		sbh.addToCollection(collectionUri, true, collDoc);
	}
}

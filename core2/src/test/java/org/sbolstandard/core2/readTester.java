package org.sbolstandard.core2;

import java.io.InputStream;

class readTester {
	static String filenameRdf 	= "writeTesterString_v1.3.rdf";
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
	static String filenameV1_15 	= "ComponentDefinitionOutput.rdf";
	static String filenameV1_16 	= "SimpleComponentDefinitionExample.rdf";
	static String filenameV1_17 	= "namespace.rdf";
	static String filenameV1_18 	= "SBOL1/SBOL1and2Test.xml";
	static String filenameV1_19 	= "toggle.rdf";


	static String path = "test/data/";

	public static void main(String[] args) {

		try {
			InputStream file = readTester.class.getResourceAsStream(path +filenameV1_19);
			if (file == null)
				file = readTester.class.getResourceAsStream("/" + path + filenameV1_19);

			//			InputStream file = readTester.class.getResourceAsStream(path + filenameV1_1);
			//SBOLReader.setURIPrefix("http://www.async.ece.utah.edu");
			//SBOLReader.setVersion("1.0");
			//SBOLReader.setTypesInURI(true);
//			SBOLDocument document1 = SBOLReader.read("/Users/myers/Downloads/michael.rdf");
//			for (ComponentDefinition componentDefinition : document1.getComponentDefinitions()) {
//				if (componentDefinition.getComponents().size()==0) continue;
//				//String original = componentDefinition.getSequenceByEncoding(Sequence.IUPAC_DNA).getElements();
//				//String implied = componentDefinition.getImpliedNucleicAcidSequence();
//				System.out.println(componentDefinition.getIdentity());
//				System.out.println("Implied: "+componentDefinition.getImpliedNucleicAcidSequence());
//			}
			
			//SBOLDocument document2 = document1.createRecursiveCopy(
			//		document1.getTopLevel(URI.create("http://sbolhub.org/col/james_test_sbol2_061015155208")));
			//ByteArrayOutputStream out = new ByteArrayOutputStream();
			//SBOLWriter.write(document1, out);//, SBOLReader.RDFV1);
			//document1 = SBOLReader.read(new ByteArrayInputStream(out.toByteArray()));
			//SBOLWriter.write(document2,(System.out));
			//ByteArrayOutputStream out = new ByteArrayOutputStream();
			//SBOLWriter.writeV1(document1, out);
			//SBOLDocument document2 = SBOLReader.read(new ByteArrayInputStream(out.toByteArray()));
			//SBOLWriter.writeV1(document2, (System.out));
			
			//			file = readTester.class.getResourceAsStream(path +filenameV1_14);
			//			if (file == null)
			//				file = readTester.class.getResourceAsStream("/" + path + filenameV1_14);
			//			document1.read(file);
			//			SBOLDocument document2 = new SBOLDocument();
			//			for (ComponentDefinition cd : document1.getComponentDefinitions()) {
			//				ComponentDefinition cd2 = (ComponentDefinition) document2.createCopy(cd);
			//				if (!cd.equals(cd2))
			//					System.out.println(cd.getIdentity() + " " + cd.equals(cd2));
			//			}
			//			for (Sequence s : document1.getSequences()) {
			//				Sequence s2 = (Sequence) document2.createCopy(s);
			//				if (!s.equals(s2))
			//					System.out.println(s.getIdentity() + " " + s.equals(s2));
			//			}
			//document1.setDefaultURIprefix("http://www.some.org");
			//document1.createCollection("abc", "1.0");
			//document1 = SBOLTestUtils.writeAndRead(document1);
			//			SBOLDocument document  = SBOLReader.read(filenameRdf);
			//			SBOLDocument document1 = SBOLReader.readRdf(filenameV1_8);
			//			SBOLDocument document2 = SBOLReader.readJson(filenameJson);
			//			SBOLDocument document3 = SBOLReader.readTurtle(filenameTurtle);

			//SBOLDocument doc = SBOLReader.read("/Users/myers/downloads/test.xml");
			//doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
			//ComponentDefinition cd = doc.getComponentDefinition("pTAK_Toggle_10","");
			//GenBank.write(cd, (System.out));
//			SBOLFactory.createSequence("http://www.abc.com", "foo", "1.0", "AGCT", Sequence.IUPAC_DNA);
//			//SBOLFactory.setSBOLDocument(new SBOLDocument());
//			SBOLFactory.createSequence("http://www.abc.com", "foo2", "1.0", "AGCT", Sequence.IUPAC_DNA);
//			SBOLFactory.write((System.out));
//			
//			GenBank.setURIPrefix("http://www.async.ece.utah.edu");
//			SBOLDocument doc = GenBank.read("/Users/myers/downloads/GenBankEx/sequence1.gb"/*pTACK_Toggle_Switch_9*/);
//			doc.write("/Users/myers/downloads/GenBankEx/sequence1.xml");
//			doc = SBOLReader.read("/Users/myers/downloads/GenBankEx/sequence1.xml");
//			doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
//			for (ComponentDefinition componentDefinition : doc.getRootComponentDefinitions()) {
//				ComponentDefinition cd = doc.getComponentDefinition(componentDefinition.getIdentity());
//				GenBank.write(cd, "/Users/myers/downloads/GenBankEx/sequence1out.gb");		
//			}
//			
//			doc = GenBank.read("/Users/myers/downloads/GenBankEx/sequence2.gb");
//			doc.write("/Users/myers/downloads/GenBankEx/sequence2.xml");
//			doc = SBOLReader.read("/Users/myers/downloads/GenBankEx/sequence2.xml");
//			doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
//			for (ComponentDefinition componentDefinition : doc.getRootComponentDefinitions()) {
//				ComponentDefinition cd = doc.getComponentDefinition(componentDefinition.getIdentity());
//				GenBank.write(cd, "/Users/myers/downloads/GenBankEx/sequence2out.gb");		
//			}
//			
//			doc = GenBank.read("/Users/myers/downloads/GenBankEx/sequence3.gb");
//			doc.write("/Users/myers/downloads/GenBankEx/sequence3.xml");
//			doc = SBOLReader.read("/Users/myers/downloads/GenBankEx/sequence3.xml");
//			doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
//			for (ComponentDefinition componentDefinition : doc.getRootComponentDefinitions()) {
//				ComponentDefinition cd = doc.getComponentDefinition(componentDefinition.getIdentity());
//				GenBank.write(cd, "/Users/myers/downloads/GenBankEx/sequence3out.gb");		
//			}
//			
//			SBOLDocument doc = GenBank.read("/Users/myers/git/libSBOLj/core2/src/test/resources/test/data/GenBank/sequence4.gb");
//			doc.write(System.out);
//			//doc.write("/Users/myers/downloads/GenBankEx/sequence4.xml");
//			//doc = SBOLReader.read("/Users/myers/downloads/GenBankEx/sequence4.xml");
//			doc.setDefaultURIprefix("http://www.async.ece.utah.edu");
//			for (ComponentDefinition componentDefinition : doc.getRootComponentDefinitions()) {
//				ComponentDefinition cd = doc.getComponentDefinition(componentDefinition.getIdentity());
//				GenBank.write(cd, System.out);	
//			}
			//SBOLWriter.write(document1,(System.out));
			//SBOLWriter.writeRDF(SBOLTestUtils.writeAndRead(document1),(System.out));

			//			SBOLWriter.writeJson(document2,(System.out));
			//			SBOLWriter.writeTurtle(document3,(System.out));

			//			URI identity = URI.create("http://www.async.ece.utah.edu/pLactetRSeq/2/0");
			//			System.out.println(SBOLReader.getParentURI(identity));

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}

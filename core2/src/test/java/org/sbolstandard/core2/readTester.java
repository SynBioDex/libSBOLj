package org.sbolstandard.core2;

import java.io.InputStream;

public class readTester {
	public static String filenameRdf 	= "writeTesterString_v1.3.rdf";
	public static String filenameJson   = "writeTesterString_v1.3.json";
	public static String filenameTurtle = "writeTesterString_v1.3.ttl";

	public static String filenameV1_1 	= "SBOL1/partial_pIKE_left_cassette.xml";
	public static String filenameV1_2 	= "SBOL1/partial_pIKE_right_casette.xml";
	public static String filenameV1_3 	= "SBOL1/partial_pIKE_right_cassette.xml";
	public static String filenameV1_4 	= "SBOL1/partial_pTAK_left_cassette.xml";
	public static String filenameV1_5 	= "SBOL1/partial_pTAK_right_cassette.xml";
	public static String filenameV1_6 	= "SBOL1/pIKE_pTAK_cassettes 2.xml";
	public static String filenameV1_7 	= "SBOL1/pIKE_pTAK_cassettes.xml";
	public static String filenameV1_8 	= "SBOL1/pIKE_pTAK_left_right_cassettes.xml";
	public static String filenameV1_9 	= "SBOL1/pIKE_pTAK_toggle_switches.xml";
	public static String filenameV1_10 	= "SBOL1/miRNA_sbol.xml";
	public static String filenameV1_11 	= "SBOL1/labhost_All.xml";
	public static String filenameV1_12 	= "SBOL1/BBa_I0462.xml";
	public static String filenameV1_13 	= "SBOL1/pACPc_invF.xml";
	public static String filenameV1_14 	= "ComponentDefinitionOutput.rdf";
	public static String filenameV1_15 	= "SimpleComponentDefinitionExample.rdf";
	public static String filenameV1_16 	= "namespace.rdf";

	public static String path = "test/data/";

	public static void main(String[] args) {
		try {
			InputStream file = readTester.class.getResourceAsStream(path +filenameV1_16);
			if (file == null)
				file = readTester.class.getResourceAsStream("/" + path + filenameV1_16);

			//			InputStream file = readTester.class.getResourceAsStream(path + filenameV1_1);
			SBOLReader.setURIPrefix("http://www.async.ece.utah.edu");
			//SBOLReader.setVersion("1.0");
			//SBOLReader.setTypesInURI(true);
			SBOLDocument document1 = SBOLReader.read(file);
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

			SBOLWriter.writeRDF(document1,(System.out));
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

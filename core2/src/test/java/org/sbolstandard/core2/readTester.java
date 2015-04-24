package org.sbolstandard.core2;

import java.io.InputStream;

public class readTester {
	public static String filenameRdf 	= "writeTesterString_v1.3.rdf";
	public static String filenameJson   = "writeTesterString_v1.3.json";
	public static String filenameTurtle = "writeTesterString_v1.3.ttl";

	public static String filenameV1_1 	= "partial_pIKE_left_cassette.xml";
	public static String filenameV1_2 	= "partial_pIKE_right_casette.xml";
	public static String filenameV1_3 	= "partial_pIKE_right_cassette.xml";
	public static String filenameV1_4 	= "partial_pTAK_left_cassette.xml";
	public static String filenameV1_5 	= "partial_pTAK_right_cassette.xml";
	public static String filenameV1_6 	= "pIKE_pTAK_cassettes 2.xml";
	public static String filenameV1_7 	= "pIKE_pTAK_cassettes.xml";
	public static String filenameV1_8 	= "pIKE_pTAK_left_right_cassettes.xml";
	public static String filenameV1_9 	= "pIKE_pTAK_toggle_switches.xml";
	public static String filenameV1_10 	= "miRNA.sbol.xml";

	public static String path = "test/data/";

	public static void main(String[] args) {
		try {
			InputStream file = readTester.class.getResourceAsStream(path +filenameV1_1);
			if (file == null)
				file = readTester.class.getResourceAsStream("/" + path + filenameV1_1);

			//			InputStream file = readTester.class.getResourceAsStream(path + filenameV1_1);
			SBOLReader.setURIPrefix("www.async.ece.utah.edu");
			SBOLDocument document1 = SBOLReader.read(file);
			//			SBOLDocument document  = SBOLReader.read(filenameRdf);
			//			SBOLDocument document1 = SBOLReader.readRdf(filenameV1_8);
			//			SBOLDocument document2 = SBOLReader.readJson(filenameJson);
			//			SBOLDocument document3 = SBOLReader.readTurtle(filenameTurtle);

			//			SBOLWriter.writeRdf(document,(System.out));
			SBOLWriter.writeRDF(document1,(System.out));
			//			SBOLWriter.writeJson(document2,(System.out));
			//			SBOLWriter.writeTurtle(document3,(System.out));

			//			URI identity = URI.create("http://www.async.ece.utah.edu/pLactetRSeq/2/0");
			//			System.out.println(SBOLReader.getParentURI(identity));

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}

package org.sbolstandard.core2.test;

import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLReader;
import org.sbolstandard.core2.SBOLWriter;

public class readTester {
	public static String filenameRdf = "writeTesterString_v1.3.rdf";
	public static String filenameJson = "writeTesterString_v1.3.json";
	public static String filenameTurtle = "writeTesterString_v1.3.ttl";

	public static void main(String[] args) {
		try {
			SBOLDocument document  = SBOLReader.read(filenameRdf);
			//			SBOLDocument document = SBOLReader.readRdf(filenameRdf);
			//			SBOLDocument document = SBOLReader.readJson(filenameJson);
			//			SBOLDocument document = SBOLReader.readTurtle(filenameTurtle);

			SBOLWriter.writeRdf(document,(System.out));
			//			SBOLWriter.writeJson(document,(System.out));
			//			SBOLWriter.writeTurtle(document,(System.out));

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

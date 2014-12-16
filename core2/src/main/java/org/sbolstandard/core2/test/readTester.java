package org.sbolstandard.core2.test;

import java.io.File;

import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLReader;
import org.sbolstandard.core2.SBOLWriter;

public class readTester {

	//public static String filePath = "/Users/tramynguyen/git/libSBOLj/core2/writeTesterString_v1.3.rdf";
	public static String filename = "writeTesterString_v1.3.rdf";

	public static void main(String[] args) {
		try {
			//File inputFile = new File(filePath);
			SBOLDocument document  = SBOLReader.read(filename);
			//SBOLDocument document2 = SBOLReader.readRdf(inputFile);

			SBOLWriter.writeRdf(document,(System.out));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

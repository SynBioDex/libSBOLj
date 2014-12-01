package org.sbolstandard.core2.test;

import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLReader;
import org.sbolstandard.core2.SBOLWriter;

//import org.sbolstandard.core2.test.rdf.*; 

public class readTester {

	public static void main(String[] args) {
		try {
			SBOLDocument document = SBOLReader.read("writeTesterString_v1.3.rdf");
			SBOLWriter.writeRdf(document,(System.out));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

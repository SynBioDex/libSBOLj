package org.sbolstandard.core2;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class SBOLGenerateFile extends SBOLAbstractTests {

	@Override
	public void runTest(final String fileName, final SBOLDocument expected, String fileType, boolean compliant) throws Exception
	{
		SBOLValidate.validateSBOL(expected, false, compliant, false);
		if (SBOLValidate.getNumErrors()>0) {
			for (String error : SBOLValidate.getErrors()) {
				System.err.println(error);
			}
			assertTrue(false);
		}
		SBOLDocument actual = SBOLTestUtils.writeAndRead(expected,compliant);
		if (!actual.equals(expected)) {
			System.out.println("Expected:"+expected.toString());
			System.out.println("Actual  :"+actual.toString());
		}
		SBOLValidate.validateSBOL(actual, false, compliant, false);
		if (SBOLValidate.getNumErrors()>0) {
			for (String error : SBOLValidate.getErrors()) {
				System.err.println(error);
			}
			assertTrue(false);
		}
		assertTrue(actual.equals(expected));
		String PATH = "src/test/resources/";
		if(fileType.equals("rdf"))
			writeRdfFile(expected, PATH + fileName);
		else if (fileType.equals("json"))
			writeJsonFile(expected, PATH + fileName);
		else if (fileType.equals("turtle"))
			writeTurtleFile(expected, PATH + fileName);
		else
			writeRdfFile(expected, PATH + fileName);
	}

	//	public static void writeRdfFile(SBOLDocument document, File fileName)
	//	{
	//		try {
	//			SBOLWriter.writeRDF(document, fileName);
	//		} catch (FileNotFoundException e) {
	//			e.printStackTrace();
	//		}
	//	}

	public static void writeRdfFile(SBOLDocument document, String fileName) throws IOException, SBOLConversionException
	{
		try {
			SBOLWriter.write(document, new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeJsonFile(SBOLDocument document, String fileName) throws IOException, SBOLConversionException
	{
		try {
			SBOLWriter.write(document, new File(fileName), SBOLDocument.JSON);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeTurtleFile(SBOLDocument document, String fileName) throws IOException, SBOLConversionException
	{
		try {
			SBOLWriter.write(document, new File(fileName), SBOLDocument.TURTLE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


}

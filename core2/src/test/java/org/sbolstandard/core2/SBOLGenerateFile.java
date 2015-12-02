package org.sbolstandard.core2;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import uk.ac.ncl.intbio.core.io.CoreIoException;

public class SBOLGenerateFile extends SBOLAbstractTests {

	@Override
	public void runTest(final String fileName, final SBOLDocument expected, String fileType) throws Exception
	{
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

	public static void writeRdfFile(SBOLDocument document, String fileName) throws XMLStreamException, FactoryConfigurationError, CoreIoException, IOException
	{
		try {
			SBOLWriter.write(document, new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeJsonFile(SBOLDocument document, String fileName) throws XMLStreamException, FactoryConfigurationError, CoreIoException, IOException
	{
		try {
			SBOLWriter.write(document, new File(fileName), SBOLReader.JSON);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeTurtleFile(SBOLDocument document, String fileName) throws XMLStreamException, FactoryConfigurationError, CoreIoException, IOException
	{
		try {
			SBOLWriter.write(document, new File(fileName), SBOLReader.TURTLE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


}

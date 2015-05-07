package org.sbolstandard.core2;

import java.io.InputStream;

public class testReadFiles
{
	public static String path 	  = "test/data/SBOL1/";
	public static String filename = "labhost_All.xml";

	public static void main(String[] args)
	{
		try
		{
			InputStream file = readTester.class.getResourceAsStream(path + filename);
			if (file == null)
				file = testReadFiles.class.getResourceAsStream("/" + path + filename);

			SBOLReader.setURIPrefix("http://genocad.org");
			SBOLDocument document1 = SBOLReader.read(file);

			SBOLWriter.writeRDF(document1,(System.out));

		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

	}

}

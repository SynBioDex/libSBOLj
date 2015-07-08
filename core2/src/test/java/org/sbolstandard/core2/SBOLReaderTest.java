package org.sbolstandard.core2;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.sbolstandard.core.SBOLValidationException;

public class SBOLReaderTest extends SBOLAbstractTests
{
	@Override
	public void runTest(final String fileName, final SBOLDocument expected, String fileType) throws Exception
	{
		InputStream resourceAsStream = SBOLReaderTest.class.getResourceAsStream(fileName);
		if (resourceAsStream == null)
			resourceAsStream = SBOLReaderTest.class.getResourceAsStream("/" + fileName);

		assert resourceAsStream != null : "Failed to find test resource '" + fileName + "'";

		try
		{
			SBOLDocument actual;
			SBOLReader.setURIPrefix("http://www.async.ece.utah.edu");

			if(fileType.equals("rdf"))
				actual = SBOLReader.readRDF(resourceAsStream);
			else if (fileType.equals("json"))
				actual = SBOLReader.readJSON(resourceAsStream);
			else if (fileType.equals("turtle"))
				actual = SBOLReader.readTurtle(resourceAsStream);
			else
				actual = SBOLReader.read(resourceAsStream);

			assertTrue(actual.equals(expected));
		}
		catch (SBOLValidationException e)
		{
			throw new AssertionError("Failed for " + fileName, e);
		}
	}

}

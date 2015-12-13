package org.sbolstandard.core2;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;

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
				actual = SBOLReader.read(resourceAsStream);
			else if (fileType.equals("json"))
				actual = SBOLReader.read(resourceAsStream,SBOLReader.JSON);
			else if (fileType.equals("turtle"))
				actual = SBOLReader.read(resourceAsStream,SBOLReader.TURTLE);
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

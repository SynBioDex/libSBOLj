package org.sbolstandard.core2;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.sbolstandard.core.SBOLValidationException;

/**
 * The two inputs for runTest will do the following:
 * 1. Read in the specified .xml file.
 * 2. Generate the specified content into a file
 * 3. Compare the two resulting files in a deep equality check to see if they are the same
 *
 * Note: These files being generated and compared to are running SBOLReader.read() which generates
 * an rdf file. If other formats were to be tested, make sure to adjust .read to any of the following
 * formats: SBOLReader.readJson() or SBOLReader.readTurtle()
 *
 * @author Tramy Nguyen
 *
 */
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

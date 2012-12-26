package org.sbolstandard.core;

import static org.junit.Assert.assertTrue;
import static org.sbolstandard.core.SBOLTestUtils.createDocument;

import java.io.FileInputStream;
import java.io.InputStream;

import org.sbolstandard.core.util.SBOLDeepEquality;

public class SBOLReaderTest extends SBOLAbstractTests {
	public void runTest(final String fileName, final SBOLRootObject... contents) throws Exception {
		SBOLDocument expected = createDocument(contents);

		// new SBOLPrettyWriter().write(expected, System.out);

        InputStream resourceAsStream = SBOLReaderTest.class.getResourceAsStream(fileName);
        if(resourceAsStream == null) resourceAsStream = SBOLReaderTest.class.getResourceAsStream("/" + fileName);

        assert resourceAsStream != null : "Failed to find test resource '" + fileName + "'";

        SBOLDocument actual = SBOLFactory.read(resourceAsStream);

		assertTrue(SBOLDeepEquality.isDeepEqual(expected, actual));
	}
}
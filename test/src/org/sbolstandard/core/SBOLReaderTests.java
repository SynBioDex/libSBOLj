package org.sbolstandard.core;

import static org.junit.Assert.assertTrue;
import static org.sbolstandard.core.SBOLTestUtils.createDocument;

import java.io.FileInputStream;

import org.sbolstandard.core.util.SBOLDeepEquality;

public class SBOLReaderTests extends SBOLAbstractTests {
	public void runTest(final String fileName, final SBOLRootObject... contents) throws Exception {
		SBOLDocument expected = createDocument(contents);

		// new SBOLPrettyWriter().write(expected, System.out);

		SBOLDocument actual = SBOLFactory.read(new FileInputStream(fileName));

		assertTrue(SBOLDeepEquality.isDeepEqual(expected, actual));
	}
}
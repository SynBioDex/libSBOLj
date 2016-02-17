package org.sbolstandard.core;

import static org.junit.Assert.assertTrue;
import static org.sbolstandard.core.SBOLTestUtils.createDocument;
import static org.sbolstandard.core.SBOLTestUtils.writeAndRead;

import org.junit.Assume;
import org.sbolstandard.core.util.SBOLDeepEquality;

public class SBOLWriterTest extends SBOLAbstractTests {
	@Override
	public void runTest(final String fileName, final SBOLRootObject... contents) throws Exception {
        assumeNotNull(contents);
		SBOLDocument expected = createDocument(contents);
		SBOLDocument actual = writeAndRead(expected);
		
		assertTrue(SBOLDeepEquality.isDeepEqual(expected, actual));
	}

    private static <A> void assumeNotNull(A a) {
        Assume.assumeNotNull(a);
    }
}
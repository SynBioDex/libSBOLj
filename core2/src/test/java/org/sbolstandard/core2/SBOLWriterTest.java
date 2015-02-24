package org.sbolstandard.core2;

import org.junit.Assume;
import org.sbolstandard.core2.abstract_classes.TopLevel;

public class SBOLWriterTest extends SBOLAbstractTests {
	@Override
	public void runTest(final String fileName, final TopLevel... contents) throws Exception {
		assumeNotNull(contents);
		SBOLDocument expected = SBOLTestUtils.createDocument(contents);
		SBOLDocument actual = SBOLTestUtils.writeAndRead(expected);

		//TODO: include deepEquality check here.
		//		assertTrue(SBOLDeepEquality.isDeepEqual(expected, actual));
	}

	private static <A> void assumeNotNull(A a) {
		Assume.assumeNotNull(a);
	}
}
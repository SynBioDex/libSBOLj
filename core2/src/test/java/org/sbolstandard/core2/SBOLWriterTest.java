package org.sbolstandard.core2;

import static org.junit.Assert.assertTrue;

import org.junit.Assume;

public class SBOLWriterTest extends SBOLAbstractTests {

	@Override
	public void runTest(final String fileName, final SBOLDocument expected, String fileType) throws Exception {
		assumeNotNull(expected);
		SBOLDocument actual = SBOLTestUtils.writeAndRead(expected);
		SBOLValidate.validateCompliance(actual);
		assertTrue(actual.equals(expected));
	}

	private static <A> void assumeNotNull(A a) {
		Assume.assumeNotNull(a);
	}

}
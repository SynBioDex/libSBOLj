package org.sbolstandard.core2;

import java.net.URI;

import org.junit.Assume;
import static org.junit.Assert.assertTrue;
import org.sbolstandard.core2.abstract_classes.TopLevel;

public class SBOLWriterTest extends SBOLAbstractTests {
	@Override
	public void runTest(final String fileName, final SBOLDocument expected) throws Exception {
		assumeNotNull(expected);
		SBOLDocument actual = SBOLTestUtils.writeAndRead(expected);
		assertTrue(actual.equals(expected));
	}

	private static <A> void assumeNotNull(A a) {
		Assume.assumeNotNull(a);
	}
}
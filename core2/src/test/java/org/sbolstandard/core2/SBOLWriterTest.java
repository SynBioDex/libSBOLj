package org.sbolstandard.core2;

import static org.junit.Assert.assertTrue;

import org.junit.Assume;

public class SBOLWriterTest extends SBOLAbstractTests {

	@Override
	public void runTest(final String fileName, final SBOLDocument expected, String fileType) throws Exception {
		assumeNotNull(expected);
		//SBOLValidate.validateCompliance(expected);
		//SBOLValidate.validateCompleteness(expected);
//		SBOLValidate.clearErrors();
//		SBOLValidate.validateOntologyUsage(expected);
//		if (SBOLValidate.getNumErrors()>0) {
//			for (String error : SBOLValidate.getErrors()) 
//				System.err.println(error);
//			assertTrue(false);
//		}
		SBOLDocument actual = SBOLTestUtils.writeAndRead(expected);
		assertTrue(actual.equals(expected));
	}

	private static <A> void assumeNotNull(A a) {
		Assume.assumeNotNull(a);
	}

}
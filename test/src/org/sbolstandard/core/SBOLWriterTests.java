package org.sbolstandard.core;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.sbolstandard.core.util.SBOLDeepEquality;

public class SBOLWriterTests extends SBOLAbstractTests {
	public void runTest(final String fileName, final SBOLRootObject... contents) throws Exception {
		SBOLDocument expected = createDocument(contents);
		
//		new SBOLPrettyWriter().write(expected, System.out);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SBOLFactory.write(expected, out);
		
		SBOLDocument actual = SBOLFactory.read(new ByteArrayInputStream(out.toByteArray())); 
		
		assertTrue(SBOLDeepEquality.isDeepEqual(expected, actual));
	}
}
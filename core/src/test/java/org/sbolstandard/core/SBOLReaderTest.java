package org.sbolstandard.core;

import static org.sbolstandard.core.SBOLTestUtils.createDocument;

import java.io.IOException;
import java.io.InputStream;

import org.sbolstandard.core.util.SBOLDeepEquality;

@SuppressWarnings("unused")
public class SBOLReaderTest extends SBOLAbstractTests {
	@Override
	public void runTest(final String fileName, final SBOLRootObject... contents) throws Exception {
		// new SBOLPrettyWriter().write(expected, System.out);

        InputStream resourceAsStream = SBOLReaderTest.class.getResourceAsStream(fileName);
        if(resourceAsStream == null) resourceAsStream = SBOLReaderTest.class.getResourceAsStream("/" + fileName);

        assert resourceAsStream != null : "Failed to find test resource '" + fileName + "'";

        try {
            SBOLDocument actual = SBOLFactory.read(resourceAsStream);

            if(contents != null) {
                SBOLDocument expected = createDocument(contents);
                new SBOLDeepEquality.EqualityTester().assertEqual(expected, actual);
            }
        } catch(SBOLDeepEquality.NotEqualException e) {
            throw new AssertionError("Failed for " + fileName, e);
        } catch(IOException e) {
            throw new AssertionError("Failed for " + fileName, e);
        } catch(SBOLValidationException e) {
            throw new AssertionError("Failed for " + fileName, e);
        }
	}
}
package org.sbolstandard.core2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all specified Test Classes.
 * @author Tramy Nguyen
 *
 */
@RunWith(Suite.class)
@SuiteClasses(
		{
			SBOLReaderTest.class,
			SBOLWriterTest.class,
			//			SBOLValidatorTest.class,
			//			SBOLDuplicateObjectTest.class
		}
		)

public class SBOLTestSuite {
}



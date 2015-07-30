package org.sbolstandard.core2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all specified Test Classes.
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 */
@RunWith(Suite.class)
@SuiteClasses(
		{
//						SBOLGenerateFile.class,
			SBOLReaderTest.class,
			SBOLWriterTest.class,
			URIcomplianceTest.class
		}
		)

public class SBOLTestSuite {
}



package org.sbolstandard.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({SBOLReaderTests.class, SBOLWriterTests.class, SBOLValidatorTests.class, SBOLDuplicateObjectTests.class})
public class SBOLTestSuite {
}

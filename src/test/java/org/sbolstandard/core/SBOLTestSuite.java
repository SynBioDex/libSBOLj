package org.sbolstandard.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({SBOLReaderTest.class, SBOLWriterTest.class, SBOLValidatorTest.class, SBOLDuplicateObjectTest.class})
public class SBOLTestSuite {
}

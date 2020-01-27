package com.cleancoder.args;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ArgsExceptionTest.class, ArgsTestCommon.class, ArgsTestBoolean.class,
        ArgsTestInteger.class, ArgsTestDouble.class, ArgsTestString.class, ArgsTestMap.class,
        ArgsTestStringArray.class})
public class TestSuite {
}

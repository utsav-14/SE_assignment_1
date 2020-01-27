package com.cleancoder.args;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static com.cleancoder.args.ArgsException.ErrorCode.*;
import static org.junit.Assert.*;


public class ArgsTestCommon {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ArgsTestCommon.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

    @Test
    public void testCreateWithNoSchemaOrArguments() throws Exception {

        Args args = new Args("", new String[0]);
        assertEquals(0, args.nextArgument());
    }


    @Test
    public void testWithNoSchemaButWithOneArgument() throws Exception {
        try {

            new Args("", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(UNEXPECTED_ARGUMENT, e.getErrorCode());
            assertEquals("x", e.getErrorArgumentId());
        }
    }

    @Test
    public void testWithNoSchemaButWithMultipleArguments() throws Exception {
        try {
            new Args("", new String[]{"-x", "-y"});
            fail();
        } catch (ArgsException e) {
            assertEquals(UNEXPECTED_ARGUMENT, e.getErrorCode());
            assertEquals("x", e.getErrorArgumentId());
        }

    }

    @Test
    public void testNonLetterSchema() throws Exception {
        try {
            new Args("*", new String[]{});
            fail("Args constructor should have thrown exception");
        } catch (ArgsException e) {
            assertEquals(INVALID_ARGUMENT_NAME, e.getErrorCode());
            assertEquals("*", e.getErrorArgumentId());
        }
    }

    @Test
    public void testInvalidArgumentFormat() throws Exception {
        try {
            new Args("f~", new String[]{});
            fail("Args constructor should have thrown exception");
        } catch (ArgsException e) {
            assertEquals(INVALID_ARGUMENT_FORMAT, e.getErrorCode());
            assertEquals("f", e.getErrorArgumentId());
        }
    }

    @Test
    public void testExtraArguments() throws Exception {
        Args args = new Args("x,y*", new String[]{"-x", "-y", "alpha", "beta"});
        assertTrue(args.getBoolean("x"));
        assertEquals("alpha", args.getString("y"));
        assertEquals(3, args.nextArgument());
    }

    @Test
    public void testExtraArgumentsThatLookLikeFlags() throws Exception {
        Args args = new Args("x,y", new String[]{"-x", "alpha", "-y", "beta"});
        assertTrue(args.has("x"));
        assertFalse(args.has("y"));
        assertTrue(args.getBoolean("x"));
        assertFalse(args.getBoolean("y"));
        assertEquals(1, args.nextArgument());
    }

}


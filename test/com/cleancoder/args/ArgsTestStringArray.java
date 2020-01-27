package com.cleancoder.args;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_STRING;
import static org.junit.Assert.*;

public class ArgsTestStringArray {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ArgsTestStringArray.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

    @Test
    public void testStringArray() throws Exception {
        Args args = new Args("x[*]", new String[]{"-x", "alpha"});
        assertTrue(args.has("x"));
        String[] result = args.getStringArray("x");
        assertEquals(1, result.length);
        assertEquals("alpha", result[0]);
    }

    @Test
    public void testMissingStringArrayElement() throws Exception {
        try {
            new Args("x[*]", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(MISSING_STRING, e.getErrorCode());
            assertEquals("x", e.getErrorArgumentId());
        }
    }

    @Test
    public void manyStringArrayElements() throws Exception {
        Args args = new Args("x[*]", new String[]{"-x", "alpha", "-x", "beta", "-x", "gamma"});
        assertTrue(args.has("x"));
        String[] result = args.getStringArray("x");
        assertEquals(3, result.length);
        assertEquals("alpha", result[0]);
        assertEquals("beta", result[1]);
        assertEquals("gamma", result[2]);
    }

    @Test
    public void testSchemaMismatchStringArray() throws Exception {
        try {
            Args args = new Args("files[*]", new String[]{"-names", "abc", "-names", "bcd", "-names", "cde"});
            assertTrue(args.has("files"));
            assertFalse(args.has("names"));
        } catch (ArgsException e) {
            assertEquals("names", e.getErrorArgumentId());
        }
    }
}

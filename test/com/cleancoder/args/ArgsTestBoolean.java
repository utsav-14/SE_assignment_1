package com.cleancoder.args;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;


public class ArgsTestBoolean {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ArgsTestBoolean.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

    @Test
    public void testSimpleBooleanPresent() throws Exception {
        Args args = new Args("x", new String[]{"-x"});
        assertTrue(args.getBoolean("x"));
        assertEquals(1, args.nextArgument());
    }

    @Test
    public void testSchemaMismatchBoolean() throws Exception {
        try {
            Args args = new Args("x", new String[]{"-k"});
            assertTrue(args.has("x"));
            assertFalse(args.has("k"));
        } catch (ArgsException e) {
            assertEquals("k", e.getErrorArgumentId());
        }
    }
}
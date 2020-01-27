package com.cleancoder.args;

import org.junit.Test;

import static org.junit.Assert.*;


public class ArgsTestBoolean {

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
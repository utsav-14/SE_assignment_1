package com.cleancoder.args;

import org.junit.Test;

import static com.cleancoder.args.ArgsException.ErrorCode.INVALID_INTEGER;
import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_INTEGER;
import static org.junit.Assert.*;

public class ArgsTestInteger {

    @Test
    public void testSimpleIntPresent() throws Exception {
        Args args = new Args("x#", new String[]{"-x", "42"});
        assertTrue(args.has("x"));
        assertEquals(42, args.getInt("x"));
        assertEquals(2, args.nextArgument());
    }

    @Test
    public void testInvalidInteger() {
        try {
            new Args("x#", new String[]{"-x", "Forty two"});
            fail();
        } catch (ArgsException e) {
            assertEquals(INVALID_INTEGER, e.getErrorCode());
            assertEquals("x", e.getErrorArgumentId());
            assertEquals("Forty two", e.getErrorParameter());
        }
    }

    @Test
    public void testMissingInteger() {
        try {
            new Args("x#", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(MISSING_INTEGER, e.getErrorCode());
            assertEquals("x", e.getErrorArgumentId());
        }
    }

    @Test
    public void testSchemaMismatchInteger() {
        try {
            Args args = new Args("port#", new String[]{"-count", "100"});
            assertTrue(args.has("port"));
            assertFalse(args.has("count"));
        } catch (ArgsException e) {
            assertEquals("count", e.getErrorArgumentId());
        }
    }
}

package com.cleancoder.args;

import org.junit.Test;

import static com.cleancoder.args.ArgsException.ErrorCode.INVALID_DOUBLE;
import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_DOUBLE;
import static org.junit.Assert.*;

public class ArgsTestDouble {

    @Test
    public void testSimpleDoublePresent() throws Exception {
        Args args = new Args("x##", new String[]{"-x", "42.3"});
        assertTrue(args.has("x"));
        assertEquals(42.3, args.getDouble("x"), .001);
    }

    @Test
    public void testInvalidDouble() {
        try {
            new Args("x##", new String[]{"-x", "Forty two.3"});
            fail();
        } catch (ArgsException e) {
            assertEquals(INVALID_DOUBLE, e.getErrorCode());
            assertEquals("x", e.getErrorArgumentId());
            assertEquals("Forty two.3", e.getErrorParameter());
        }
    }

    @Test
    public void testMissingDouble() {
        try {
            new Args("x##", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(MISSING_DOUBLE, e.getErrorCode());
            assertEquals("x", e.getErrorArgumentId());
        }
    }

    @Test
    public void testSchemaMismatchDouble() {
        try {
            Args args = new Args("cost##", new String[]{"-fraction", "0.8"});
            assertTrue(args.has("cost"));
            assertFalse(args.has("fraction"));
        } catch (ArgsException e) {
            assertEquals("fraction", e.getErrorArgumentId());
        }
    }
}

package com.cleancoder.args;

import org.junit.Test;

import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_STRING;
import static org.junit.Assert.*;

public class ArgsTestString {

    @Test
    public void testSimpleStringPresent() throws Exception {
        Args args = new Args("x*", new String[]{"-x", "param"});
        assertTrue(args.has("x"));
        assertEquals("param", args.getString("x"));
        assertEquals(2, args.nextArgument());
    }

    @Test
    public void testMissingStringArgument() {
        try {
            new Args("directory*", new String[]{"-directory"});
            fail();
        } catch (ArgsException e) {
            assertEquals(MISSING_STRING, e.getErrorCode());
            assertEquals("directory", e.getErrorArgumentId());
        }
    }

    @Test
    public void testSchemaMismatchString() {
        try {
            Args args = new Args("directory*", new String[]{"-path"});
            assertTrue(args.has("directory"));
            assertFalse(args.has("path"));
        } catch (ArgsException e) {
            assertEquals("path", e.getErrorArgumentId());
        }
    }

    @Test
    public void testMissingStringFollowedByDifferentElement() {
        try {
            Args args = new Args("directory*,port#", new String[]{"-directory", "-port", "100"});
            assertTrue(args.has("directory"));
            assertFalse(args.has("port"));
            assertEquals("-port", args.getString("directory"));
        } catch (ArgsException e) {
            assertEquals(MISSING_STRING, e.getErrorCode());
        }
    }
}

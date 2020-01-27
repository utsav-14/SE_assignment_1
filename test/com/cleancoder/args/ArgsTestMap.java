package com.cleancoder.args;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ArgsTestMap {

    @Test
    public void MapArgument() throws Exception {
        Args args = new Args("f&", new String[]{"-f", "key1:val1,key2:val2"});
        assertTrue(args.has("f"));
        Map<String, String> map = args.getMap("f");
        assertEquals("val1", map.get("key1"));
        assertEquals("val2", map.get("key2"));
    }

    @Test(expected = ArgsException.class)
    public void malFormedMapArgument() throws Exception {
        Args args = new Args("f&", new String[]{"-f", "key1:val1,key2"});
    }

    @Test
    public void oneMapArgument() throws Exception {
        Args args = new Args("f&", new String[]{"-f", "key1:val1"});
        assertTrue(args.has("f"));
        Map<String, String> map = args.getMap("f");
        assertEquals("val1", map.get("key1"));
    }

    @Test
    public void testSchemaMismatchMap() {
        try {
            Args args = new Args("dict&", new String[]{"-map", "k1:v1,k2:v2"});
            assertTrue(args.has("dict"));
            assertFalse(args.has("map"));
        } catch (ArgsException e) {
            assertEquals("map", e.getErrorArgumentId());
        }
    }
}

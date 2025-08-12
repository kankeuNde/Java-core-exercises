package com.rnk.generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PairTest {
    private Pair<String, String> pairOfString;
    private Pair<String, Integer> pairOfString_Integer;

    @BeforeEach
    public void setUp(){
        pairOfString = new Pair<>("key", "value");
        pairOfString_Integer = new Pair<>("key", 123);
    }

    @Test
    public void testPairOfStringCreation(){
        pairOfString = new Pair<>("key", "value");
        assertNotNull(pairOfString);
    }

    @Test
    public void testGetKeyString(){
        String key = pairOfString.getKey();
        assertEquals("key", key);
    }

    @Test
    public void testGetValueString(){
        String value = pairOfString.getValue();
        assertEquals("value", value);
    }

    @Test
    public void testSetKeyString(){
        pairOfString.setKey("New key");
        assertEquals("New key", pairOfString.getKey());
    }

    @Test
    public void testSetValueString(){
        pairOfString.setValue("New value");
        assertEquals("New value", pairOfString.getValue());
    }

    @Test
    public void testPairOfString_IntegerCreation(){
        pairOfString_Integer = new Pair<>("key", 123);
        assertNotNull(pairOfString_Integer);
    }

    @Test
    public void testGetKeyString_Integer(){
        String key = pairOfString_Integer.getKey();
        assertEquals("key", key);
    }

    @Test
    public void testGetValueString_Integer(){
        Integer value = pairOfString_Integer.getValue();
        assertEquals(123, value);
    }

    @Test
    public void testSetKeyString_Integer(){
        pairOfString_Integer.setKey("New key");
        assertEquals("New key", pairOfString_Integer.getKey());
    }

    @Test
    public void testSetValueString_Integer(){
        pairOfString_Integer.setValue(234);
        assertEquals(234, pairOfString_Integer.getValue());
    }

    @Test
    public void testSet_null_key(){
        assertThrows(IllegalArgumentException.class,() -> pairOfString.setKey(null));
    }

    @Test
    public void testToString(){
        Pair<String, Integer> pair = new Pair<>("Code", 42);
        String text = pair.toString();
        assertTrue(text.contains("Code"));
        assertTrue(text.contains("42"));
    }

    @Test
    public void testNotEqualDifferentPairs(){
        assertNotEquals(new Pair<>("a", 1), new Pair<>("b", 1));
        assertNotEquals(new Pair<>("a", 1), new Pair<>("a", 2));
    }

    @Test
    public void testEqualsAndHashCode(){
        Pair<String, Integer> p1 = new Pair<>("key", 123);
        Pair<String, Integer> p2 = new Pair<>(new String("key"), 123);
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testAllowsNullValue(){
        Pair<String, String> pair = new Pair<>("key", null);
        assertNull(pair.getValue());
    }
}

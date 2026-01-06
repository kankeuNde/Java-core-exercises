package com.rnk.algorithms;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyLinkedHashMapTest {

    @Test
    public void givenLinkedHashMap_whenRemovesEldestEntry_thenCorrect(){
        LinkedHashMap<Integer, String> map = new MyLinkedHashMap<>(16, .75f, true);
        map.put(1, null);
        map.put(2, null);
        map.put(3, null);
        map.put(4, null);
        map.put(5, null);

        Set<Integer> keys = map.keySet();
        assertEquals("[1, 2, 3, 4, 5]", keys.toString());

        map.put(6, null);
        assertEquals("[2, 3, 4, 5, 6]", map.keySet().toString());

        map.put(7, null);
        assertEquals("[3, 4, 5, 6, 7]", map.keySet().toString());

        map.put(8, null);
        assertEquals("[4, 5, 6, 7, 8]", map.keySet().toString());
    }

}

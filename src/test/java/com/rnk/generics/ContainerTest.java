package com.rnk.generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    private Container<String> stringContainer;
    private Container<Integer> integerContainer;

    @BeforeEach
    public void setUp(){
        stringContainer = new Container<String>("Test String");
        integerContainer = new Container<Integer>(123);
    }

    @Test
    public void testGettingString(){
        String item = stringContainer.getItem();
        assertEquals("Test String", item);
    }

    @Test
    public void testSetting_withValidValue(){
        stringContainer.setItem("New value");
        String item = stringContainer.getItem();
        assertEquals("New value", item);
    }

    @Test
    public void testSetting_with_null_value_should_throw_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> stringContainer.setItem(null));
    }

    @Test
    public void testGettingInteger(){
        Integer item = integerContainer.getItem();
        assertEquals(123, item);
    }
    @Test
    public void testSetting_with_validInteger(){
        integerContainer.setItem(1234);
        assertEquals(1234, integerContainer.getItem());
    }
    @Test
    public void testSettingInteger_with_null_value_should_throw_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->integerContainer.setItem(null));
    }

    @Test
    public void testNewContainer_with_null_object(){
        assertThrows(IllegalArgumentException.class, ()->new Container<>(null));
    }

    @Test
    public void testContainerStoresReferenceNotCopy(){
        StringBuilder sb = new StringBuilder("Test");
        Container<StringBuilder> sbc = new Container<>(sb);
        sb.append(" updated");
        assertEquals("Test updated", sbc.getItem().toString());
    }


}

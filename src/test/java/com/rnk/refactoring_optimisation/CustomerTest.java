package com.rnk.refactoring_optimisation;

import com.rnk.refactoring_optimisation.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {

    @Test
    void shouldCreateValidCustomer(){
        Customer customer = new Customer("1", "Alice", "alice@test.com");
        assertEquals("Alice", customer.getName());
    }

    @Test
    void shouldThrowExceptionForInvalidEmail(){
        assertThrows(IllegalArgumentException.class, ()-> new Customer("2", "Bob", "invalid-email"));
    }

    @Test
    void shouldThrowExceptionForEmptyName(){
        assertThrows(IllegalArgumentException.class, () -> new Customer("3", "", "valid@test.com"));
    }

    @Test
    void shouldThrowExceptionForNullId(){
        assertThrows(IllegalArgumentException.class, () -> new Customer(null, "Alice", "alice@test.com"));
    }

    @Test
    void shouldThrowExceptionForNullEmail(){
        assertThrows(IllegalArgumentException.class, () -> new Customer("4", "Alice", null));
    }
}

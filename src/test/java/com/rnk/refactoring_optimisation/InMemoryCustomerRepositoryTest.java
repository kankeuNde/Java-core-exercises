package com.rnk.refactoring_optimisation;

import com.rnk.refactoring_optimisation.exception.DuplicateCustomerException;
import com.rnk.refactoring_optimisation.model.Customer;
import com.rnk.refactoring_optimisation.model.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryCustomerRepositoryTest {
    private  Logger logger = Logger.getLogger(InMemoryCustomerRepositoryTest.class.getName());
    InMemoryCustomerRepository customers;

    @BeforeEach
    public void setUp(){
        customers = new InMemoryCustomerRepository(logger);
        customers.save(new Customer("123-123", "Rodrigue Kankeu", "rodriguekankeu@gmail.com"));
    }

    @Test
    public void testSave_with_valid_Custumer(){
        Customer c1 = new Customer("123-321", "Tagne Andre", "andretagne@gmail.com");
        customers.save(c1);
        assertEquals(2, customers.findAll().size());
    }

    @Test
    public void testSave_with_duplicate_ID_should_throw_DuplicateCustomerException(){
        Customer c = new Customer("123-123", "Rodrigue Kankeu", "rodriguekankeu@gmail.com");
        assertThrows(DuplicateCustomerException.class, ()->customers.save(c));
    }

    @Test
    public void testFindById_with_Existing_ID(){
        assertTrue(customers.findById("123-123").isPresent());
    }

    @Test
    public void testFindById_with_NonExisting_ID_returns_OptionalEmpty(){
        assertTrue(customers.findById("not-exist").isEmpty());
    }

    @Test
    public void testFindAll(){
        assertEquals(1, customers.findAll().size());
    }
}

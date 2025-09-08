package com.rnk.refactoring_optimisation;

import com.rnk.refactoring_optimisation.exception.CustomerNotFoundException;
import com.rnk.refactoring_optimisation.exception.DuplicateCustomerException;
import com.rnk.refactoring_optimisation.model.Customer;
import com.rnk.refactoring_optimisation.model.CustomerRepository;
import com.rnk.refactoring_optimisation.model.InMemoryCustomerRepository;
import com.rnk.refactoring_optimisation.service.CustomerService;
import com.rnk.refactoring_optimisation.util.CustomLoggerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerServiceTest {
    private CustomLoggerImpl logger = new CustomLoggerImpl(Logger.getLogger(CustomerServiceTest.class.getName()));
    private CustomerRepository customerRepository = new InMemoryCustomerRepository(Logger.getLogger(CustomerServiceTest.class.getName()));
    private CustomerService customerService;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        this.customerService = new CustomerService(customerRepository, logger);
        customer = customerService.registerCustomer("1234-4321", "John Doe", "johndoe@email.com");
    }

    @Test
    public void testRegisterCustomer_with_valid_data() {
        assertEquals("1234-4321", customer.getId());
        assertEquals("John Doe", customer.getName());
        assertEquals("johndoe@email.com", customer.getEmail());
    }

    @Test
    public void testRegisterCustomer_with_invalid_data() {
        assertThrows(IllegalArgumentException.class, () -> customerService.registerCustomer("1223-4321", "John Doe", "johndoe.com"));
    }

    @Test
    public void shouldThrowDuplicateCustomerExceptionWhenRegisteringSameId() {
        assertThrows(DuplicateCustomerException.class, () -> customerService.registerCustomer("1234-4321", "John Doe", "johndoe@email.com"));
    }

    @Test
    public void testGetCustomer() {
        Customer customer = customerService.getCustomer("1234-4321");
        assertEquals("John Doe", customer.getName());
        assertEquals("johndoe@email.com", customer.getEmail());
    }

    @Test
    public void testGet_NonExisting_customer_shouldThrow_CustomerNotFoundException() {
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer("Not-Existing"));
    }

    @Test
    public void testListCustomers() {
        customerService.registerCustomer("1234-4322", "John Doe", "johndoe@email.com");
        List<Customer> customers = customerService.listCustomers();
        assertEquals(2, customers.size());
    }
}

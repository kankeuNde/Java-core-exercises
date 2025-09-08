package com.rnk.generics;

import com.rnk.generics.filter.CustomerFilter;
import com.rnk.generics.filter.Specification;
import com.rnk.refactoring_optimisation.util.CustomLogger;
import com.rnk.refactoring_optimisation.util.CustomLoggerImpl;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

import com.rnk.generics.exceptions.*;

public class InMemoryRepositoryTest {
    private Repository<Integer, String> repository;
    private Repository<Integer, Customer> customerRepository;
    private static final CustomLogger logger = new CustomLoggerImpl(Logger.getLogger(InMemoryRepository.class.getName()));
    private Specification montrealSpec;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryRepository<>(logger);
        customerRepository = new InMemoryRepository<>(logger);
        customerRepository.save(1, new Customer(1, "Name one", "Montreal"));
        customerRepository.save(2, new Customer(2, "Name two", "Quebec"));
        customerRepository.save(3, new Customer(3, "Name three", "Montreal"));
        customerRepository.save(4, new Customer(4, "Name four", "Quebec"));
        customerRepository.save(5, new Customer(5, "Name five", "Montreal"));
        customerRepository.save(6, new Customer(6, "Name six", "Quebec"));
        montrealSpec = new CustomerFilter(new Customer(7, "", "Montreal"));
    }

    @Test
    public void testSaveAndFindById() {
        repository.save(1, "Customer A");
        assertEquals("Customer A", repository.findById(1));
    }

    @Test
    public void testFindAll() {
        repository.save(1, "Entity1");
        repository.save(2, "Entity2");
        List<String> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains("Entity1"));
        assertTrue(all.contains("Entity2"));
    }

    @Test
    public void testDeleteByIdExisting() {
        repository.save(1, "Entity1");
        repository.deleteById(1);
        assertThrows(EntityNotFoundException.class, () -> repository.findById(1));
    }

    @Test
    public void testDeleteByIdNonExisting() {
        assertThrows(EntityNotFoundException.class, () -> repository.deleteById(99));
    }

    @Test
    public void testFindByIdNonExisting() {
        assertThrows(EntityNotFoundException.class, () -> repository.findById(42));
    }

    @Test
    public void testSaveNullIdShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> repository.save(null, "Entity"));
    }

    @Test
    public void testSaveNullEntityShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> repository.save(1, null));
    }

    @Test
    public void testFindAllCustomerByPredicate_onCity() {
        List<Customer> allCustomer = customerRepository.findBy(c -> c.getCity().equals("Quebec"));
        assertEquals(3, allCustomer.size());
    }

    @Test
    public void testFindAllCustomerByPredicate_onName() {
        List<Customer> allCustomer = customerRepository.findBy(c -> c.getName().endsWith("one"));
        assertEquals(1, allCustomer.size());
    }

    @Test
    public void testFindAllCustomerByPredicate_ThrowsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> customerRepository.findBy(c -> c.getName().endsWith("seven")));
    }

    @Test
    public void testFindAllCustomerByPredicate_onNull(){
        assertThrows(IllegalArgumentException.class, ()->customerRepository.findBy(null));
    }

    @Test
    public void testCreateInMemoryRepository_with_null_Logger_throws_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new InMemoryRepository<Integer, Customer>(null));
    }

    @Test
    public void testFindAllCustomerBySpecification(){
        List<Customer> filteredBySpecification = customerRepository.findBySpecification(montrealSpec);
        assertEquals(3, filteredBySpecification.size());
    }

    @Test
    public void testFindAllCustomerBySpecification_onName() {
        List<Customer> allCustomer = customerRepository.findBySpecification(new CustomerFilter(new Customer(1, "Name one", null)));
        assertEquals(1, allCustomer.size());
    }
}

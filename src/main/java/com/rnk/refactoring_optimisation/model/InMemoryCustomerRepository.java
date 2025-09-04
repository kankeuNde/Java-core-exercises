package com.rnk.refactoring_optimisation.model;

import com.rnk.refactoring_optimisation.exception.DuplicateCustomerException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Logger logger;
    Map<String, Customer> customers;

    public InMemoryCustomerRepository(Logger logger){
        if(logger == null)
            throw new IllegalArgumentException("Logger cannot be null");
        this.logger = logger;
        logger.info("InMemoryCustomerRepository created");
        customers = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Customer customer) throws DuplicateCustomerException {
        Objects.requireNonNull(customer, "Customer cannot be null");
        if (customers.containsKey(customer.getId())) {
            logger.warning(() -> "Already existing customer with Id: " + customer.getId());
            throw new DuplicateCustomerException("Customer already exists: " + customer.getId());
        }
        logger.info("Saving customer with ID: " + customer.getId());
        customers.put(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> findById(String id) {
        if(customers.containsKey(id)) {
            logger.info("Customer with ID: "+id+" found.");
            return Optional.of(customers.get(id));
        }
        logger.info("Customer with ID: "+id+" not found.");
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        Collection<Customer> customers = this.customers.values();
        logger.info("List of "+ this.customers.size()+" customers found.");
        return new ArrayList<>(customers);
    }
}

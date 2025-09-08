package com.rnk.refactoring_optimisation.service;

import com.rnk.refactoring_optimisation.exception.CustomerNotFoundException;
import com.rnk.refactoring_optimisation.model.Customer;
import com.rnk.refactoring_optimisation.model.CustomerRepository;
import com.rnk.refactoring_optimisation.util.CustomLoggerImpl;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private CustomerRepository customerRepository;
    private final CustomLoggerImpl logger;

    public CustomerService(CustomerRepository customerRepository, CustomLoggerImpl logger) {
        this.customerRepository = customerRepository;
        this.logger = logger;
    }

    public Customer registerCustomer(String id, String name, String email){
        Customer customer = new Customer(id, name, email);
        customerRepository.save(customer);
        logger.info("Registered new customer with ID: " + id);
        return customer;
    }

    public Customer getCustomer(String id){
        Optional<Customer> found = customerRepository.findById(id);
        if (found.isEmpty()) {
            logger.warn("Customer with ID: " + id + " not found.");
            throw new CustomerNotFoundException("Customer not found: " + id);
        }
        logger.info("Customer with ID: " + id + "found.");
        return found.get();
    }

    public List<Customer> listCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        logger.info("Return a list of "+customerList.size()+" customer(s)");
        return customerList;
    }
}

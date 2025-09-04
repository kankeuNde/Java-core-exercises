package com.rnk.refactoring_optimisation.model;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void save(Customer customer);
    Optional<Customer> findById(String id);
    List<Customer> findAll();
}

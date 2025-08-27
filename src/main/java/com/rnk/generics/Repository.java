package com.rnk.generics;

import com.rnk.generics.exceptions.EntityNotFoundException;
import com.rnk.generics.filter.Specification;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<ID, T> {
    void save (ID id, T entity) throws IllegalArgumentException;
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    List<T> findBy(Predicate<T> filter) throws EntityNotFoundException;
    List<T> findBySpecification(Specification specification);
}

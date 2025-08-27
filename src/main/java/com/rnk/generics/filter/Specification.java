package com.rnk.generics.filter;

public interface Specification<T> {
    boolean isSatisfiedBy(T item);
}

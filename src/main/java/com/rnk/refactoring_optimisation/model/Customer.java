package com.rnk.refactoring_optimisation.model;

import java.util.regex.Pattern;

public class Customer {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final String id;
    private final String name;
    private final String email;

    public Customer(String id, String name, String email) {
        if (id == null || id.isBlank()){
            throw new IllegalArgumentException("Customer id cannot be null or empty");
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid customer email format");
        }

        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

package com.rnk.design_patterns.decorator;

public abstract class Drink {
    protected String description;
    public abstract double cost();
    public String getDescription() {
        return description;
    }

}

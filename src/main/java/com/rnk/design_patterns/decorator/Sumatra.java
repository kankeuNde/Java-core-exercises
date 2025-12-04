package com.rnk.design_patterns.decorator;

public class Sumatra extends Drink{
    public Sumatra() {
        description = "Sumatra";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double cost() {
        return 11;
    }
}

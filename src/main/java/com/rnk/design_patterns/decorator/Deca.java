package com.rnk.design_patterns.decorator;

public class Deca extends Drink{

    public Deca() {
        description = "Deca";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double cost() {
        return 13;
    }
}

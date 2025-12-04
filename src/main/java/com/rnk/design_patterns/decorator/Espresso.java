package com.rnk.design_patterns.decorator;

public class Espresso extends Drink{
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double cost() {
        return 12;
    }
}

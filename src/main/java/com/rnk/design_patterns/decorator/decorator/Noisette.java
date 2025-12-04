package com.rnk.design_patterns.decorator.decorator;

import com.rnk.design_patterns.decorator.Drink;

public class Noisette extends DrinkDecorator{
    public Noisette(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return 1.6 + drink.cost();
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + " with Noisette";
    }
}

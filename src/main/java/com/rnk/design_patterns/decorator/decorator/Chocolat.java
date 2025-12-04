package com.rnk.design_patterns.decorator.decorator;

import com.rnk.design_patterns.decorator.Drink;

public class Chocolat extends DrinkDecorator{
    public Chocolat(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return 1.0 + drink.cost();
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + " with chocolate";
    }
}

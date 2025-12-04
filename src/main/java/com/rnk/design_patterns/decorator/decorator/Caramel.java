package com.rnk.design_patterns.decorator.decorator;

import com.rnk.design_patterns.decorator.Drink;

public class Caramel extends DrinkDecorator{

    public Caramel(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return 1.8 + drink.cost();
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + " with caramel";
    }
}

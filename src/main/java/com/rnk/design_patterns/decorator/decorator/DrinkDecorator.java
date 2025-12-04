package com.rnk.design_patterns.decorator.decorator;

import com.rnk.design_patterns.decorator.Drink;

public abstract class DrinkDecorator extends Drink{
    public DrinkDecorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public abstract String getDescription();

    protected Drink drink;
}

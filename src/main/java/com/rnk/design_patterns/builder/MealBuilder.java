package com.rnk.design_patterns.builder;

import com.rnk.design_patterns.builder.burger.ChickenBurger;
import com.rnk.design_patterns.builder.burger.VegBurger;
import com.rnk.design_patterns.builder.drinks.Coke;
import com.rnk.design_patterns.builder.drinks.Pepsi;

public class MealBuilder {
    public Meal prepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }


}

package com.rnk.design_patterns.decorator;

import com.rnk.design_patterns.decorator.decorator.Caramel;
import com.rnk.design_patterns.decorator.decorator.Chocolat;
import com.rnk.design_patterns.decorator.decorator.Noisette;

public class Test {
    public static void main(String[] args) {
        Drink drink;
        drink = new Noisette(new Caramel(new Chocolat(new Noisette(new Sumatra()))));
        System.out.println(drink.getDescription());
        System.out.println(drink.cost());
    }
}

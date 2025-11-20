package com.rnk.design_patterns.builder.drinks;

import com.rnk.design_patterns.builder.packaging.Bottle;
import com.rnk.design_patterns.builder.Item;
import com.rnk.design_patterns.builder.Packing;

public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}

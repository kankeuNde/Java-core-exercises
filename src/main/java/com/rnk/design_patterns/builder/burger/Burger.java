package com.rnk.design_patterns.builder.burger;

import com.rnk.design_patterns.builder.Item;
import com.rnk.design_patterns.builder.Packing;
import com.rnk.design_patterns.builder.packaging.Wrapper;

public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}

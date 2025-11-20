package com.rnk.design_patterns.builder.packaging;

import com.rnk.design_patterns.builder.Packing;

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}

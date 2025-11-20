package com.rnk.design_patterns.builder.packaging;

import com.rnk.design_patterns.builder.Packing;

public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}

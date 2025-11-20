package com.rnk.design_patterns.abstract_factory.smarttech;

import com.rnk.design_patterns.abstract_factory.Light;

public class SmartTechLight implements Light {

    @Override
    public String setup() {
        return "SmartTech Light";
    }
}

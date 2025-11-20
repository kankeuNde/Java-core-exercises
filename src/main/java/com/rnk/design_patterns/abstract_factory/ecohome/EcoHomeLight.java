package com.rnk.design_patterns.abstract_factory.ecohome;

import com.rnk.design_patterns.abstract_factory.Light;

public class EcoHomeLight implements Light {
    @Override
    public String setup() {
        return "EcoHome Light";
    }
}

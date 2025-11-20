package com.rnk.design_patterns.abstract_factory.ecohome;

import com.rnk.design_patterns.abstract_factory.Thermostat;

public class EcoHomeThermostat implements Thermostat {
    @Override
    public String setup() {
        return "EcoHome thermostat";
    }
}

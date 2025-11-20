package com.rnk.design_patterns.abstract_factory.smarttech;

import com.rnk.design_patterns.abstract_factory.Thermostat;

public class SmartTechThermostat implements Thermostat {
    @Override
    public String setup() {
        return "SmartTech Thermostat";
    }
}

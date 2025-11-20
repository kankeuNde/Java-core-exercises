package com.rnk.design_patterns.abstract_factory;

import com.rnk.design_patterns.abstract_factory.smarttech.SmartTechLight;
import com.rnk.design_patterns.abstract_factory.smarttech.SmartTechThermostat;

public class SmartTechFactory implements DeviceFactory{
    @Override
    public Light createLight() {
        return new SmartTechLight();
    }

    @Override
    public Thermostat createThermostat() {
        return new SmartTechThermostat();
    }
}

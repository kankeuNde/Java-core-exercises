package com.rnk.design_patterns.abstract_factory;

import com.rnk.design_patterns.abstract_factory.ecohome.EcoHomeLight;
import com.rnk.design_patterns.abstract_factory.ecohome.EcoHomeThermostat;

public class EcoHomeFactory implements DeviceFactory{
    @Override
    public Light createLight() {
        return new EcoHomeLight();
    }

    @Override
    public Thermostat createThermostat() {
        return new EcoHomeThermostat();
    }
}

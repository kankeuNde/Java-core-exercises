package com.rnk.design_patterns.abstract_factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EcoHomeFactoryTest {

    @Test
    public void testEcoHomeFactory_returnLightObject(){
        Light light = new EcoHomeFactory().createLight();
        assertTrue(light.setup().contains("EcoHome"));
    }

    @Test
    public void testEcoHomeFactory_returnThermostatObject(){
        Thermostat thermostat = new EcoHomeFactory().createThermostat();
        assertTrue(thermostat.setup().contains("EcoHome"));
    }
}

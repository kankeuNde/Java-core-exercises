package com.rnk.design_patterns.abstract_factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmartTechFactoryTest {

    @Test
    public void testSmartTechFactory_returnLightObject(){
        Light light = new SmartTechFactory().createLight();
        assertTrue(light.setup().contains("SmartTech"));
    }

    @Test
    public void testSmartTechFactory_returnThermostatObject(){
        Thermostat thermostat = new SmartTechFactory().createThermostat();
        assertTrue(thermostat.setup().contains("SmartTech"));
    }
}

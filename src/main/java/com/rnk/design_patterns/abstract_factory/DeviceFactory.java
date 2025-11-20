package com.rnk.design_patterns.abstract_factory;

public interface DeviceFactory {
    Light createLight();
    Thermostat createThermostat();
}

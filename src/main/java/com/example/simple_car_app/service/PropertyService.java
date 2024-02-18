package com.example.simple_car_app.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final Environment environment;

    public PropertyService(Environment environment) {
        this.environment = environment;
    }

    public String getPropertyByName(String name) {
        return environment.getProperty(name);
    }
}

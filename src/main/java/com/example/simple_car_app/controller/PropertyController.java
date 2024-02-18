package com.example.simple_car_app.controller;

import com.example.simple_car_app.service.PropertyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/{name}")
    String getProperty(@PathVariable String name) {
        return propertyService.getPropertyByName(name);
    }
}

package com.example.simple_car_app.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CarRequest {

    @NotEmpty(message = "Car name required")
    private String carName;

    @NotEmpty(message = "Car brand required")
    private String carBrand;

    @NotEmpty(message = "Car price required")
    private int carPrice;

    public CarRequest(String carName, String carBrand, int carPrice) {
        this.carName = carName;
        this.carBrand = carBrand;
        this.carPrice = carPrice;
    }
}

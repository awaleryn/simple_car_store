package com.example.simple_car_app.request;

import lombok.Data;

@Data
public class CarPatchRequest {

    private String carName;
    private String carBrand;
    private Integer carPrice;

    public CarPatchRequest(String carName, String carBrand, int carPrice) {
        this.carName = carName;
        this.carBrand = carBrand;
        this.carPrice = carPrice;
    }
}

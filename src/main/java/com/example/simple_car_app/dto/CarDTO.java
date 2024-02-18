package com.example.simple_car_app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDTO {

    private String carName;
    private String carBrand;
    private int carPrice;

    public CarDTO(String carName, String carBrand, int carPrice) {
        this.carName = carName;
        this.carBrand = carBrand;
        this.carPrice = carPrice;
    }
}

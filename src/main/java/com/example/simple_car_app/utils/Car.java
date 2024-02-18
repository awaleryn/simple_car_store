package com.example.simple_car_app.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carName;
    private String carBrand;
    private int carPrice;

    public Car(String carName, String carBrand, int carPrice) {
        this.carName = carName;
        this.carBrand = carBrand;
        this.carPrice = carPrice;
    }
}

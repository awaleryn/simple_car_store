package com.example.simple_car_app.service;

import com.example.simple_car_app.dto.CarDTO;
import com.example.simple_car_app.request.CarPatchRequest;
import com.example.simple_car_app.request.CarRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<CarDTO> getAllCars();

    CarDTO getCarDTOById(Long id);

    CarDTO addNewCar(CarRequest carRequest);

    CarDTO updateCarField(Long id, CarPatchRequest carPatchRequest);

    void deleteCar(Long id);
}

package com.example.simple_car_app.controller;

import com.example.simple_car_app.dto.CarDTO;
import com.example.simple_car_app.request.CarPatchRequest;
import com.example.simple_car_app.request.CarRequest;
import com.example.simple_car_app.service.CarService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/{id}")
    public CarDTO getCarById(@PathVariable Long id) {
        return carService.getCarDTOById(id);
    }

    @RolesAllowed("ADMIN")
    @PostMapping
    public CarDTO addCar(@Valid @RequestBody CarRequest carRequest) {
        return carService.addNewCar(carRequest);
    }

    @RolesAllowed("ADMIN")
    @PatchMapping("/{id}")
    public CarDTO updateCarField(@PathVariable Long id, @RequestBody CarPatchRequest carPatchRequest) {
        return carService.updateCarField(id, carPatchRequest);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car deleted");
    }
}

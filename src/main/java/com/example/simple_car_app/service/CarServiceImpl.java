package com.example.simple_car_app.service;

import com.example.simple_car_app.dto.CarDTO;
import com.example.simple_car_app.dto.CarMapper;
import com.example.simple_car_app.repository.CarRepository;
import com.example.simple_car_app.request.CarPatchRequest;
import com.example.simple_car_app.request.CarRequest;
import com.example.simple_car_app.utils.Car;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carMapper.carListToCarDtoList(carRepository.findAll());
    }

    @Override
    public CarDTO getCarDTOById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Car with provided ID " + id + " not found"));
        return carMapper.carToDto(car);
    }

    @Override
    @Transactional
    public CarDTO addNewCar(CarRequest carRequest) {
        Car car = carMapper.carRequestToCar(carRequest);
        carRepository.save(car);
        return carMapper.carToDto(car);
    }

    @Override
    @Transactional
    public CarDTO updateCarField(Long id, CarPatchRequest carPatchRequest) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            if (carPatchRequest.getCarName() != null) {
                car.setCarName(carPatchRequest.getCarName());
            }
            if (carPatchRequest.getCarBrand() != null) {
                car.setCarBrand(carPatchRequest.getCarBrand());
            }
            if (carPatchRequest.getCarPrice() != 0) {
                car.setCarPrice(carPatchRequest.getCarPrice());
            }
            carRepository.save(car);
            return carMapper.carToDto(car);
        } else {
            throw new NoSuchElementException("Car with provided ID " + id + " not found");
        }
    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}

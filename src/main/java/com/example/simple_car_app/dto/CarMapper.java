package com.example.simple_car_app.dto;

import com.example.simple_car_app.request.CarRequest;
import com.example.simple_car_app.utils.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    public CarDTO carToDto(Car car) {
        CarDTO carDTO = new CarDTO();

        carDTO.setCarName(car.getCarName());
        carDTO.setCarBrand(car.getCarBrand());
        carDTO.setCarPrice(car.getCarPrice());

        return carDTO;
    }

    public List<CarDTO> carListToCarDtoList(List<Car> carList) {
        return carList.stream()
                .map(this::carToDto)
                .collect(Collectors.toList());
    }

    public Car carRequestToCar(CarRequest carRequest) {
        Car car = new Car();
        car.setCarName(carRequest.getCarName());
        car.setCarBrand(carRequest.getCarBrand());
        car.setCarPrice(carRequest.getCarPrice());

        return car;
    }
}

package com.example.simple_car_app.carTest;

import com.example.simple_car_app.controller.CarController;
import com.example.simple_car_app.dto.CarDTO;
import com.example.simple_car_app.dto.CarMapper;
import com.example.simple_car_app.repository.CarRepository;
import com.example.simple_car_app.service.CarService;
import com.example.simple_car_app.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private CarMapper carMapper;
    @Mock
    private CarService carService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        carService = new CarServiceImpl(carRepository, carMapper);
    }

    @Test
    public void testGetCarById() {
        MockitoAnnotations.initMocks(this);

        Long carId = 1L;
        CarDTO expectedCarDTO = new CarDTO("Car1", "Brand1", 10000);
        when(carService.getCarDTOById(carId)).thenReturn(expectedCarDTO);

        CarController carController = new CarController(carService);
        CarDTO resultCarDTO = carController.getCarById(carId);

        assertEquals(expectedCarDTO.getCarName(), resultCarDTO.getCarName());
        assertEquals(expectedCarDTO.getCarBrand(), resultCarDTO.getCarBrand());
        assertEquals(expectedCarDTO.getCarPrice(), resultCarDTO.getCarPrice());
    }


    @Test
    public void testDeleteCar() {
        // Given
        Long carId = 1L;

        // When
        carService.deleteCar(carId);

        // Then
        verify(carRepository, times(1)).deleteById(carId);
    }
}

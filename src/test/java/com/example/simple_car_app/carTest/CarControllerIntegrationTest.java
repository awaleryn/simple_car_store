package com.example.simple_car_app.carTest;

import com.example.simple_car_app.controller.CarController;
import com.example.simple_car_app.dto.CarDTO;
import com.example.simple_car_app.request.CarRequest;
import com.example.simple_car_app.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CarController.class)
public class CarControllerIntegrationTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    void whenGetAllCars_thenReturnsCarList() throws Exception {
        // Mock data
        CarDTO carDTO = new CarDTO("Car1", "Brand1", 20000);
        when(carService.getAllCars()).thenReturn(Collections.singletonList(carDTO));

        // Perform GET request with authorization header
        mockMvc.perform(MockMvcRequestBuilders.get("/car")
                        .header("Authorization", "Basic YWRtaW46cGFzcw==") // Basic auth header for admin user
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carName").value("Car1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carBrand").value("Brand1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carPrice").value(20000));
    }

    @Test
    void whenAddCar_thenAddsCar() throws Exception {
        // Mock data
        CarRequest carRequest = new CarRequest("Car1", "Brand1", 20000);
        CarDTO carDTO = new CarDTO("Car1", "Brand1", 20000);
        when(carService.addNewCar(any(CarRequest.class))).thenReturn(carDTO);

        // Perform POST request with authorization header
        mockMvc.perform(MockMvcRequestBuilders.post("/car")
                        .header("Authorization", "Basic YWRtaW46cGFzcw==") // Basic auth header for admin user
                        .content(asJsonString(carRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.carName").value("Car1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carBrand").value("Brand1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carPrice").value(20000));
    }

    // Utility method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

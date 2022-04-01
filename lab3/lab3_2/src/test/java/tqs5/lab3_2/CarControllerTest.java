package tqs5.lab3_2;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;

import main.java.tqs5.lab3_2.Car;
import main.java.tqs5.lab3_2.CarController;
import main.java.tqs5.lab3_2.CarManagerService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;
import java.util.*;

@WebMvcTest(CarController.class)
class CarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @Test
    void createCar() throws IOException, Exception {
        Car camaro = new Car("Chevrolet", "Camaro");
        when(service.save(Mockito.any())).thenReturn(camaro);

        mvc.perform(
                post("/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(camaro)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("Camaro")));

        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void getCars() throws Exception {
        Car camaro = new Car("Chevrolet", "Camaro");
        Car a3 = new Car("Audi", "A3");

        List<Car> allCars = Arrays.asList(camaro, a3);
        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
                get("/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model", is(camaro.getModel())))
                .andExpect(jsonPath("$[1].model", is(a3.getModel())));
        
        verify(service, times(1)).getAllCars();
    }

    @Test
    void getCarById() throws Exception {
        Car camaro =new Car("Chevrolet", "Camaro");

        when(service.getCarDetails(0L)).thenReturn(camaro);

        mvc.perform(
                get("/cars/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("Camaro")));

        verify(service, times(1)).getCarDetails(0L);
    }
}

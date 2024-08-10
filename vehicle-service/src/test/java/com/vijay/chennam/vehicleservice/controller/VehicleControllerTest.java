package com.vijay.chennam.vehicleservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijay.chennam.vehicleservice.model.Vehicle;
import com.vijay.chennam.vehicleservice.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    private final String BASE_URL = "/vehicle";

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void saveVehicle() throws Exception {
        Vehicle vehicle = getVehicle();

        when(vehicleService.saveVehicle((any(Vehicle.class)))).thenReturn(vehicle);

        mockMvc.perform(post(BASE_URL + "/saveVehicle")
                        .content(asJsonString(vehicle))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(vehicle)))
                .andExpect(status().isCreated());
    }

    @Test
    void getVehicleById() throws Exception {

        Vehicle vehicle = getVehicle();
        when(vehicleService.getVehicleById(1L)).thenReturn(vehicle);

        mockMvc.perform(get(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(vehicle)))
                .andExpect(status().isOk());
    }

    @Test
    void getVehicleByMake() throws Exception {

        Vehicle vehicle = getVehicle();
        String make = "Tata";
        List<Vehicle> vehicles = Arrays.asList(vehicle);

        when(vehicleService.getVehicleByMake(make)).thenReturn(vehicles);

        mockMvc
                .perform(get(BASE_URL + "/make/{make}", make)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(vehicles)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllVehicles() throws Exception {
        Vehicle vehicle = getVehicle();

        List<Vehicle> vehicles = Arrays.asList(vehicle);
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        mockMvc
                .perform(get(BASE_URL + "/getAllVehicles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(vehicles)));
    }

    private Vehicle getVehicle() {
        Vehicle vehicle = Vehicle.builder()
                .id(1L)
                .make("Tata")
                .model("Safari")
                .color("Black")
                .price(2550000)
                .year(2024)
                .build();

        vehicle.setChassisNumber("uq3ehi9e6");
        return vehicle;
    }

    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
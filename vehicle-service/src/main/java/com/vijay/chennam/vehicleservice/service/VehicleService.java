package com.vijay.chennam.vehicleservice.service;

import com.vijay.chennam.vehicleservice.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle getVehicleById(Long id);

    List<Vehicle> getAllVehicles();

    List<Vehicle> getVehicleByMake(String make);

}
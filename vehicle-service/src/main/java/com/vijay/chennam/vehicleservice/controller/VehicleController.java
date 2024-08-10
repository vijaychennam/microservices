package com.vijay.chennam.vehicleservice.controller;

import com.vijay.chennam.vehicleservice.model.Vehicle;
import com.vijay.chennam.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/saveVehicle")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle saveVehicle(@RequestBody Vehicle vehicle) {
        System.out.println();
        return vehicleService.saveVehicle(vehicle);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vehicle getVehicleById(@PathVariable("id") Long id) {
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("/make/{make}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vehicle> getVehicleByMake(@PathVariable("make") String make) {
        return vehicleService.getVehicleByMake(make);
    }

    @GetMapping("/getAllVehicles")
    @ResponseStatus(HttpStatus.OK)
    List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
}

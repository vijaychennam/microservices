package com.vijay.chennam.showroomservice.controller;

import com.vijay.chennam.showroomservice.dto.Vehicle;
import com.vijay.chennam.showroomservice.model.PurchaseRequest;
import com.vijay.chennam.showroomservice.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showroom")
public class ShowroomController {

    @Autowired
    private RestService restService;

    @GetMapping("/vehicles")
    @ResponseStatus(HttpStatus.OK)
    public List<Vehicle> getAllVehicles() {

        return restService.getAllVehicles();
    }

    @GetMapping("/vehiclesByMake/{vehicleBrand}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vehicle> getVehicleByBrand(@PathVariable("vehicleBrand") String vehicleBrand) {
        return restService.getVehicleByMake(vehicleBrand);
    }

    @GetMapping("/vehiclesById/{vehicleId}")
    @ResponseStatus(HttpStatus.OK)
    public Vehicle getVehicleById(@PathVariable("vehicleId") Long vehicleId) {
        return restService.getVehicleById(vehicleId);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyVehicle(@RequestBody PurchaseRequest purchaseRequest) {
        String message = restService.buyVehicle(purchaseRequest);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
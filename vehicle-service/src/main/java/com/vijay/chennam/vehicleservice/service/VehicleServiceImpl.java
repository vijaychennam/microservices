package com.vijay.chennam.vehicleservice.service;

import com.vijay.chennam.vehicleservice.exception.VehicleNotFoundException;
import com.vijay.chennam.vehicleservice.model.Vehicle;
import com.vijay.chennam.vehicleservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {

        List<Vehicle> vehicles = vehicleRepository.findAll()
                .stream().sorted(Comparator.comparing(Vehicle::getMake)).toList();
        if (vehicles.isEmpty()) {
            throw new VehicleNotFoundException("No vehicles found");
        } else {
            return vehicles;
        }
    }

    @Override
    public Vehicle getVehicleById(Long id) {

        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found by provided id"));
    }

    @Override
    public List<Vehicle> getVehicleByMake(String make) {

        String formatdMake = null;

        if (make != null && !make.isEmpty()) {
            formatdMake = make.substring(0, 1).toUpperCase() + make.substring(1).toLowerCase();
        }
        List<Vehicle> vehiclesByBrand = vehicleRepository.findByMake(formatdMake);
        return Optional.of(vehiclesByBrand)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new VehicleNotFoundException("No vehicles found for the specified brand"));
    }

}

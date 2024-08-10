package com.vijay.chennam.vehicleservice.repository;

import com.vijay.chennam.vehicleservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByMake(String make);
}

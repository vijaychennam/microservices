package com.vijay.chennam.showroomservice.service;

import com.vijay.chennam.showroomservice.dto.Sale;
import com.vijay.chennam.showroomservice.dto.Vehicle;
import com.vijay.chennam.showroomservice.exception.VehicleNotAvailabeException;
import com.vijay.chennam.showroomservice.model.PurchaseRequest;
import com.vijay.chennam.showroomservice.util.ShowroomUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestService {

    private final RestTemplate restTemplate;


    public List<Vehicle> getAllVehicles() {

        Vehicle[] vehicles = restTemplate
                .getForObject(ShowroomUtil.ALL_VEHICLES_URL, Vehicle[].class);

        if (vehicles != null) {
            return Arrays.asList(vehicles);
        } else {
            throw new RuntimeException("No vehicles found");
        }
    }

    public List<Vehicle> getVehicleByMake(String vehicleBrand) {

        Vehicle[] vehicles = restTemplate.getForObject(ShowroomUtil.VEHICLE_BY_MAKE_URL + vehicleBrand, Vehicle[].class);
        if (vehicles != null) {
            return Arrays.asList(vehicles);
        } else {
            throw new RuntimeException("No vehicles found for make " + vehicleBrand);
        }
    }

    public Vehicle getVehicleById(Long vehicleId) {

        if (vehicleId != null) {
            Vehicle v = null;
            return restTemplate.getForObject(ShowroomUtil.VEHICLE_BY_ID_URL + vehicleId, Vehicle.class);
        } else {
            throw new RuntimeException("No vehicles found for the id " + vehicleId);
        }
    }

    @Transactional
    public String buyVehicle(PurchaseRequest purchaseRequest) {
        boolean isAvailable = inventoryCheck(purchaseRequest.getVehicleId());

        if (isAvailable) {
            int quantityChange = purchaseRequest.getQuantity();
            Sale sale = restTemplate
                    .postForObject(ShowroomUtil.CREATE_SALE_URL, purchaseRequest, Sale.class);

            if (sale != null) {

                String updateInventoryUrl = UriComponentsBuilder.fromHttpUrl(ShowroomUtil.UPDATE_INVENTORY_URL).pathSegment(String.valueOf(purchaseRequest.getVehicleId())).queryParam("quantityChange", quantityChange).toUriString();

                restTemplate.postForEntity(updateInventoryUrl, null, Void.class);
                return "Vehicle purchased successfully. Enjoy your rides!";
            } else {
                throw new RuntimeException("Vehicle not sale");
            }
        } else {
            throw new VehicleNotAvailabeException();
        }
    }

    private boolean inventoryCheck(Long vehicleId) {

        Boolean b = restTemplate
                .getForObject(ShowroomUtil.INVENTORY_CHECK_URL + vehicleId, Boolean.class);
        return Boolean.TRUE.equals(b);
    }

}
package com.vijay.chennam.salesservice.service;

import com.vijay.chennam.salesservice.dto.PurchaseRequest;
import com.vijay.chennam.salesservice.dto.Vehicle;
import com.vijay.chennam.salesservice.model.Sale;
import com.vijay.chennam.salesservice.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class SaleService {

    @Autowired
    private SaleRepository salesRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Sale createSale(PurchaseRequest purchaseRequest) {
        Sale sale = Sale.builder()
                .vehicleId(purchaseRequest.getVehicleId())
                .customerId(purchaseRequest.getCustomerId())
                .quantity(purchaseRequest.getQuantity())
                .customerName(purchaseRequest.getCustomerName())
                .totalPrice(calclulatePrice(purchaseRequest.getVehicleId()))
                .quantity(purchaseRequest.getQuantity())
                .saleDate(LocalDateTime.now()).build();

        return salesRepository.save(sale);
    }

    private double calclulatePrice(Long vehicleId) {
        Vehicle vehicle = restTemplate.getForObject("http://localhost:9091/vehicle/" + vehicleId, Vehicle.class);
        return vehicle.getPrice();
    }
}
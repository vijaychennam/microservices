package com.vijay.chennam.inventoryservice.service;

import com.vijay.chennam.inventoryservice.model.Inventory;
import com.vijay.chennam.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean checkStock(Long vehicleId) {
        return inventoryRepository
                .findByVehicleId(vehicleId)
                .map(inventory -> inventory.getStock() > 0).orElse(false);
    }

    public void updateStock(Long vehicleId, int quantityChange) {

        Inventory inventory = inventoryRepository
                .findByVehicleId(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found in inventory"));

        if (inventory.getStock() >= quantityChange) {
            inventory.setStock(inventory.getStock() - quantityChange);
            inventoryRepository.save(inventory);
        } else {
            throw new RuntimeException("Stock is lower than the requested quantity: " + inventory.getStock() + " Please try less than " + quantityChange);
        }
    }
}
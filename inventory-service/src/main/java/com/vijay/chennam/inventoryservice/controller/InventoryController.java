package com.vijay.chennam.inventoryservice.controller;

import com.vijay.chennam.inventoryservice.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/check/{vehicleId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean checkStock(@PathVariable("vehicleId") Long vehicleId) {

        return inventoryService.checkStock(vehicleId);
    }

    @PostMapping("/update/{vehicleId}")
    public ResponseEntity<Void> updateStock(@PathVariable("vehicleId") Long vehicleId, @RequestParam int quantityChange) {

        inventoryService.updateStock(vehicleId, quantityChange);
        return ResponseEntity.noContent().build();
    }
}

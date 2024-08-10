package com.vijay.chennam.salesservice.controller;

import com.vijay.chennam.salesservice.dto.PurchaseRequest;
import com.vijay.chennam.salesservice.model.Sale;
import com.vijay.chennam.salesservice.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SaleService salesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sale createSale(@RequestBody PurchaseRequest purchaseRequest) {
        return salesService.createSale(purchaseRequest);
    }
}
package com.vijay.chennam.salesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {

    private Long id;
    private Long vehicleId;
    private Long customerId;
    private String customerName;
    private int quantity;
    private Double price;
}

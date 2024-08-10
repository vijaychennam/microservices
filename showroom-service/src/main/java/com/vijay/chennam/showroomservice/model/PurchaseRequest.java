package com.vijay.chennam.showroomservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {

    private Long vehicleId;
    private Long customerId;
    private String customerName;
    private int quantity;
}

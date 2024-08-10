package com.vijay.chennam.showroomservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    private Long id;
    private Long vehicleId;
    private Long customerId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime saleDate;
}

package com.vijay.chennam.salesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private String make;
    private String model;
    private int year;
    private String color;
    private double price;
    private String chassisNumber;
}

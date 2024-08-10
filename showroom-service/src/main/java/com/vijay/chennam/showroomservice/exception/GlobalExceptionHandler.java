package com.vijay.chennam.showroomservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleNotAvailabeException.class)
    public ResponseEntity<String> handleVehicleNotFoundException() {
        return new ResponseEntity<>("Vehicle is out of stock, Mean while make an order. Will notify you soon!", HttpStatus.BAD_REQUEST);
    }
}

package com.vijay.chennam.inventoryservice.exception;

public class StockNotFoundException extends RuntimeException {
    String message;

    public StockNotFoundException(String message) {
        super();
        this.message = message;
    }
}

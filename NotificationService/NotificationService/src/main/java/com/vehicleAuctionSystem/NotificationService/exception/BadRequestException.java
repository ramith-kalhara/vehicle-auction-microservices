package com.vehicleAuctionSystem.NotificationService.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
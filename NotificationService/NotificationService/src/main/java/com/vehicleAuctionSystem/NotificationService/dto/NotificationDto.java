package com.vehicleAuctionSystem.NotificationService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {

    private Long id;
    private String title;
    private String message;
    private String type;
    private String recipient;
    private String status;
    private Boolean isRead;
    private LocalDateTime createdAt;
}
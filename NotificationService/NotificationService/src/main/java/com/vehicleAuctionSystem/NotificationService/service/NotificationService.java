package com.vehicleAuctionSystem.NotificationService.service;

import com.vehicleAuctionSystem.NotificationService.dto.NotificationDto;

import java.util.List;

public interface NotificationService {

    NotificationDto createNotification(NotificationDto notificationDto);

    List<NotificationDto> getAllNotifications();

    NotificationDto getNotificationById(Long id);

    NotificationDto updateNotification(Long id, NotificationDto notificationDto);

    Boolean deleteNotification(Long id);
}
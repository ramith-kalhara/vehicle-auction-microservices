package com.vehicleAuctionSystem.NotificationService.service.impl;

import com.vehicleAuctionSystem.NotificationService.dto.NotificationDto;
import com.vehicleAuctionSystem.NotificationService.entity.Notification;
import com.vehicleAuctionSystem.NotificationService.exception.BadRequestException;
import com.vehicleAuctionSystem.NotificationService.exception.NotFoundException;
import com.vehicleAuctionSystem.NotificationService.mapper.NotificationMapper;
import com.vehicleAuctionSystem.NotificationService.repository.NotificationRepository;
import com.vehicleAuctionSystem.NotificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {

        if (notificationDto.getTitle() == null || notificationDto.getTitle().trim().isEmpty()) {
            throw new BadRequestException("Notification title is required");
        }

        if (notificationDto.getMessage() == null || notificationDto.getMessage().trim().isEmpty()) {
            throw new BadRequestException("Notification message is required");
        }

        if (notificationDto.getType() == null || notificationDto.getType().trim().isEmpty()) {
            throw new BadRequestException("Notification type is required");
        }

        if (notificationDto.getRecipient() == null || notificationDto.getRecipient().trim().isEmpty()) {
            throw new BadRequestException("Notification recipient is required");
        }

        Notification notification = notificationMapper.toEntity(notificationDto);

        if (notification.getCreatedAt() == null) {
            notification.setCreatedAt(LocalDateTime.now());
        }

        if (notification.getIsRead() == null) {
            notification.setIsRead(false);
        }

        if (notification.getStatus() == null || notification.getStatus().trim().isEmpty()) {
            notification.setStatus("PENDING");
        }

        Notification savedNotification = notificationRepository.save(notification);

        return notificationMapper.toDto(savedNotification);
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationMapper.toDtoList(notificationRepository.findAll());
    }

    @Override
    public NotificationDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found with ID: " + id));

        return notificationMapper.toDto(notification);
    }

    @Override
    public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {

        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found with ID: " + id));

        notificationMapper.updateNotificationFromDto(notificationDto, existingNotification);

        Notification savedNotification = notificationRepository.save(existingNotification);

        return notificationMapper.toDto(savedNotification);
    }

    @Override
    public Boolean deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new NotFoundException("Notification not found with ID: " + id);
        }

        notificationRepository.deleteById(id);
        return true;
    }
}
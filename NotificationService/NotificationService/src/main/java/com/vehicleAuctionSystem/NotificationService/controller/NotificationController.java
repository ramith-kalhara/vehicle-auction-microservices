package com.vehicleAuctionSystem.NotificationService.controller;

import com.vehicleAuctionSystem.NotificationService.dto.NotificationDto;
import com.vehicleAuctionSystem.NotificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notification")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        System.out.println("Notification Details : " + notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificationService.createNotification(notificationDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<NotificationDto>> getAllNotifications() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(notificationService.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(notificationService.getNotificationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> updateNotification(@PathVariable Long id,
                                                              @RequestBody NotificationDto notificationDto) {
        NotificationDto updatedNotification = notificationService.updateNotification(id, notificationDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteNotification(@PathVariable Long id) {
        Boolean isDeleted = notificationService.deleteNotification(id);
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }
}
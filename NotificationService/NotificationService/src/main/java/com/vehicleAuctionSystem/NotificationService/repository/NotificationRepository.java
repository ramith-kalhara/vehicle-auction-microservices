package com.vehicleAuctionSystem.NotificationService.repository;

import com.vehicleAuctionSystem.NotificationService.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
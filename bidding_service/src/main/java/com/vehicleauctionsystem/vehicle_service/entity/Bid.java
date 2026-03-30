package com.vehicleauctionsystem.bidding_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bids")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long vehicleId;

    @Column(nullable = false)
    private Double bidAmount;

    @Column(nullable = false)
    private LocalDateTime bidTime;

    @Column(nullable = false)
    private String status;
}
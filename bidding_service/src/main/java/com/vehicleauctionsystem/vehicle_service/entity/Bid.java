package com.vehicleauctionsystem.bidding_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bids")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bid {

    @Id
    private String id;

    private Long userId;
    private Long vehicleId;
    private Double bidAmount;
    private LocalDateTime bidTime;
    private String status;
}
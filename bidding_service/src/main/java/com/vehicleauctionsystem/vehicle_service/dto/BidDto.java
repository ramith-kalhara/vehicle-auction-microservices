package com.vehicleauctionsystem.bidding_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidDto {

    private Long id;
    private Long userId;
    private Long vehicleId;
    private Double bidAmount;
    private LocalDateTime bidTime;
    private String status;
}
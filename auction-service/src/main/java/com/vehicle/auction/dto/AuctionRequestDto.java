package com.vehicle.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionRequestDto {
    private Long vehicleId;
    private Double startPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

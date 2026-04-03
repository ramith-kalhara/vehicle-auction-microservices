package com.vehicle.auction.dto;

import com.vehicle.auction.entity.AuctionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionResponseDto {
    private Long auctionId;
    private Long vehicleId;
    private Double startPrice;
    private Double currentHighestBid;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AuctionStatus status;
}

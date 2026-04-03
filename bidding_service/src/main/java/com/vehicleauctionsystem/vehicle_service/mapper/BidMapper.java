package com.vehicleauctionsystem.bidding_service.mapper;

import com.vehicleauctionsystem.bidding_service.dto.BidDto;
import com.vehicleauctionsystem.bidding_service.entity.Bid;
import org.springframework.stereotype.Component;

@Component
public class BidMapper {

    public BidDto toDto(Bid bid) {
        if (bid == null) {
            return null;
        }

        return BidDto.builder()
                .id(bid.getId())
                .userId(bid.getUserId())
                .vehicleId(bid.getVehicleId())
                .bidAmount(bid.getBidAmount())
                .bidTime(bid.getBidTime())
                .status(bid.getStatus())
                .build();
    }

    public Bid toEntity(BidDto dto) {
        if (dto == null) {
            return null;
        }

        return Bid.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .vehicleId(dto.getVehicleId())
                .bidAmount(dto.getBidAmount())
                .bidTime(dto.getBidTime())
                .status(dto.getStatus())
                .build();
    }
}
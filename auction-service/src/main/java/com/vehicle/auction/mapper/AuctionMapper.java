package main.java.com.vehicle.auction.mapper;

import com.vehicle.auction.dto.AuctionRequestDto;
import com.vehicle.auction.dto.AuctionResponseDto;
import com.vehicle.auction.entity.Auction;
import com.vehicle.auction.entity.AuctionStatus;
import org.springframework.stereotype.Component;

@Component
public class AuctionMapper {

    public Auction toEntity(AuctionRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Auction.builder()
                .vehicleId(dto.getVehicleId())
                .startPrice(dto.getStartPrice())
                .currentHighestBid(dto.getStartPrice()) // Initial bid is start price
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .status(AuctionStatus.PENDING) // Default state
                .build();
    }

    public AuctionResponseDto toDto(Auction entity) {
        if (entity == null) {
            return null;
        }
        return AuctionResponseDto.builder()
                .auctionId(entity.getAuctionId())
                .vehicleId(entity.getVehicleId())
                .startPrice(entity.getStartPrice())
                .currentHighestBid(entity.getCurrentHighestBid())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .status(entity.getStatus())
                .build();
    }
}

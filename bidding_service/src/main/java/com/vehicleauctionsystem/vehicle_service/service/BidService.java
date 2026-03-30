package com.vehicleauctionsystem.bidding_service.service;

import com.vehicleauctionsystem.bidding_service.dto.BidDto;

import java.util.List;

public interface BidService {

    BidDto createBid(BidDto bidDto);

    BidDto getBidById(String id);

    List<BidDto> getAllBids();

    List<BidDto> getBidsByVehicleId(Long vehicleId);

    BidDto getHighestBidByVehicleId(Long vehicleId);

    BidDto updateBid(String id, BidDto bidDto);

    void deleteBid(String id);
}
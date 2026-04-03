package com.vehicle.auction.service;

import com.vehicle.auction.dto.AuctionRequestDto;
import com.vehicle.auction.dto.AuctionResponseDto;

import java.util.List;

public interface AuctionService {
    AuctionResponseDto createAuction(AuctionRequestDto auctionRequestDto);

    List<AuctionResponseDto> getAllAuctions();

    AuctionResponseDto getAuctionById(Long id);

    AuctionResponseDto startAuction(Long id);

    AuctionResponseDto endAuction(Long id);

    List<AuctionResponseDto> getActiveAuctions();
}

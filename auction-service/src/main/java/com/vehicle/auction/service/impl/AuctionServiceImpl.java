package com.vehicle.auction.service.impl;

import com.vehicle.auction.dto.AuctionRequestDto;
import com.vehicle.auction.dto.AuctionResponseDto;
import com.vehicle.auction.entity.Auction;
import com.vehicle.auction.entity.AuctionStatus;
import com.vehicle.auction.exception.ResourceNotFoundException;
import com.vehicle.auction.mapper.AuctionMapper;
import com.vehicle.auction.repository.AuctionRepository;
import com.vehicle.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    @Override
    @Transactional
    public AuctionResponseDto createAuction(AuctionRequestDto auctionRequestDto) {
        if (auctionRequestDto.getStartTime() != null && auctionRequestDto.getEndTime() != null &&
                auctionRequestDto.getStartTime().isAfter(auctionRequestDto.getEndTime())) {
            throw new IllegalArgumentException("Start time cannot be after end time");
        }
        Auction auction = auctionMapper.toEntity(auctionRequestDto);
        Auction savedAuction = auctionRepository.save(auction);
        return auctionMapper.toDto(savedAuction);
    }

    @Override
    public List<AuctionResponseDto> getAllAuctions() {
        return auctionRepository.findAll().stream()
                .map(auctionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuctionResponseDto getAuctionById(Long id) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found with id: " + id));
        return auctionMapper.toDto(auction);
    }

    @Override
    @Transactional
    public AuctionResponseDto startAuction(Long id) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found with id: " + id));

        if (auction.getStatus() != AuctionStatus.PENDING) {
            throw new IllegalArgumentException("Only pending auctions can be started");
        }

        auction.setStatus(AuctionStatus.ACTIVE);
        // Optional: Update start time if it was originally in the future
        if (auction.getStartTime() != null && auction.getStartTime().isAfter(LocalDateTime.now())) {
            auction.setStartTime(LocalDateTime.now());
        }
        Auction updatedAuction = auctionRepository.save(auction);
        return auctionMapper.toDto(updatedAuction);
    }

    @Override
    @Transactional
    public AuctionResponseDto endAuction(Long id) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found with id: " + id));

        if (auction.getStatus() == AuctionStatus.ENDED) {
            throw new IllegalArgumentException("Auction is already ended");
        }

        auction.setStatus(AuctionStatus.ENDED);
        if (auction.getEndTime() != null && auction.getEndTime().isAfter(LocalDateTime.now())) {
            auction.setEndTime(LocalDateTime.now());
        }
        Auction updatedAuction = auctionRepository.save(auction);
        return auctionMapper.toDto(updatedAuction);
    }

    @Override
    public List<AuctionResponseDto> getActiveAuctions() {
        return auctionRepository.findByStatus(AuctionStatus.ACTIVE).stream()
                .map(auctionMapper::toDto)
                .collect(Collectors.toList());
    }
}

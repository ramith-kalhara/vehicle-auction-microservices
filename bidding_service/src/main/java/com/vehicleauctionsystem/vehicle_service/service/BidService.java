package com.vehicleauctionsystem.bidding_service.service.impl;

import com.vehicleauctionsystem.bidding_service.dto.BidDto;
import com.vehicleauctionsystem.bidding_service.entity.Bid;
import com.vehicleauctionsystem.bidding_service.exception.BadRequestException;
import com.vehicleauctionsystem.bidding_service.exception.ResourceNotFoundException;
import com.vehicleauctionsystem.bidding_service.mapper.BidMapper;
import com.vehicleauctionsystem.bidding_service.repository.BidRepository;
import com.vehicleauctionsystem.bidding_service.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final BidMapper bidMapper;

    @Override
    public BidDto createBid(BidDto bidDto) {
        validateBidInput(bidDto);

        Bid highestBid = bidRepository
                .findTopByVehicleIdOrderByBidAmountDesc(bidDto.getVehicleId())
                .orElse(null);

        if (highestBid != null && bidDto.getBidAmount() <= highestBid.getBidAmount()) {
            throw new BadRequestException(
                    "Bid amount must be greater than current highest bid: " + highestBid.getBidAmount()
            );
        }

        Bid bid = bidMapper.toEntity(bidDto);
        bid.setBidTime(LocalDateTime.now());

        if (bid.getStatus() == null || bid.getStatus().isBlank()) {
            bid.setStatus("ACTIVE");
        }

        Bid savedBid = bidRepository.save(bid);
        return bidMapper.toDto(savedBid);
    }

    @Override
    public BidDto getBidById(Long id) {
        Bid bid = bidRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found with id: " + id));
        return bidMapper.toDto(bid);
    }

    @Override
    public List<BidDto> getAllBids() {
        return bidRepository.findAll()
                .stream()
                .map(bidMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BidDto> getBidsByVehicleId(Long vehicleId) {
        return bidRepository.findByVehicleIdOrderByBidAmountDesc(vehicleId)
                .stream()
                .map(bidMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BidDto getHighestBidByVehicleId(Long vehicleId) {
        Bid highestBid = bidRepository.findTopByVehicleIdOrderByBidAmountDesc(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("No bids found for vehicle id: " + vehicleId));

        return bidMapper.toDto(highestBid);
    }

    @Override
    public BidDto updateBid(Long id, BidDto bidDto) {
        validateBidInput(bidDto);

        Bid existingBid = bidRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found with id: " + id));

        Bid highestOtherBid = bidRepository
                .findTopByVehicleIdAndIdNotOrderByBidAmountDesc(bidDto.getVehicleId(), id)
                .orElse(null);

        if (highestOtherBid != null && bidDto.getBidAmount() <= highestOtherBid.getBidAmount()) {
            throw new BadRequestException(
                    "Updated bid amount must be greater than current highest other bid: "
                            + highestOtherBid.getBidAmount()
            );
        }

        existingBid.setUserId(bidDto.getUserId());
        existingBid.setVehicleId(bidDto.getVehicleId());
        existingBid.setBidAmount(bidDto.getBidAmount());
        existingBid.setStatus(
                bidDto.getStatus() == null || bidDto.getStatus().isBlank()
                        ? "ACTIVE"
                        : bidDto.getStatus()
        );

        if (existingBid.getBidTime() == null) {
            existingBid.setBidTime(LocalDateTime.now());
        }

        Bid updatedBid = bidRepository.save(existingBid);
        return bidMapper.toDto(updatedBid);
    }

    @Override
    public void deleteBid(Long id) {
        Bid bid = bidRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found with id: " + id));

        bidRepository.delete(bid);
    }

    private void validateBidInput(BidDto bidDto) {
        if (bidDto.getUserId() == null) {
            throw new BadRequestException("User ID is required");
        }

        if (bidDto.getVehicleId() == null) {
            throw new BadRequestException("Vehicle ID is required");
        }

        if (bidDto.getBidAmount() == null) {
            throw new BadRequestException("Bid amount is required");
        }

        if (bidDto.getBidAmount() <= 0) {
            throw new BadRequestException("Bid amount must be greater than 0");
        }
    }
}
package com.vehicle.auction.controller;

import com.vehicle.auction.dto.AuctionRequestDto;
import com.vehicle.auction.dto.AuctionResponseDto;
import com.vehicle.auction.service.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
@Tag(name = "Auction APIs", description = "REST APIs for managing vehicle auctions")
public class AuctionController {

    private final AuctionService auctionService;

    @Operation(summary = "Create a new auction")
    @PostMapping
    public ResponseEntity<AuctionResponseDto> createAuction(@RequestBody AuctionRequestDto auctionRequestDto) {
        AuctionResponseDto createdAuction = auctionService.createAuction(auctionRequestDto);
        return new ResponseEntity<>(createdAuction, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all auctions")
    @GetMapping
    public ResponseEntity<List<AuctionResponseDto>> getAllAuctions() {
        return ResponseEntity.ok(auctionService.getAllAuctions());
    }

    @Operation(summary = "Get active auctions")
    @GetMapping("/active")
    public ResponseEntity<List<AuctionResponseDto>> getActiveAuctions() {
        return ResponseEntity.ok(auctionService.getActiveAuctions());
    }

    @Operation(summary = "Get auction by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AuctionResponseDto> getAuctionById(@PathVariable Long id) {
        return ResponseEntity.ok(auctionService.getAuctionById(id));
    }

    @Operation(summary = "Start an auction")
    @PutMapping("/{id}/start")
    public ResponseEntity<AuctionResponseDto> startAuction(@PathVariable Long id) {
        return ResponseEntity.ok(auctionService.startAuction(id));
    }

    @Operation(summary = "End an auction")
    @PutMapping("/{id}/end")
    public ResponseEntity<AuctionResponseDto> endAuction(@PathVariable Long id) {
        return ResponseEntity.ok(auctionService.endAuction(id));
    }

    @Operation(summary = "Delete an auction")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuction(id);
        return ResponseEntity.noContent().build();
    }
}

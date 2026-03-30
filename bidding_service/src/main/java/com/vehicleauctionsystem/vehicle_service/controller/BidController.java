package com.vehicleauctionsystem.bidding_service.controller;

import com.vehicleauctionsystem.bidding_service.dto.BidDto;
import com.vehicleauctionsystem.bidding_service.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BidController {

    private final BidService bidService;

    @PostMapping
    public ResponseEntity<BidDto> createBid(@RequestBody BidDto bidDto) {
        BidDto savedBid = bidService.createBid(bidDto);
        return new ResponseEntity<>(savedBid, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BidDto>> getAllBids() {
        return ResponseEntity.ok(bidService.getAllBids());
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<BidDto>> getBidsByVehicleId(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(bidService.getBidsByVehicleId(vehicleId));
    }

    @GetMapping("/vehicle/{vehicleId}/highest")
    public ResponseEntity<BidDto> getHighestBidByVehicleId(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(bidService.getHighestBidByVehicleId(vehicleId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BidDto> getBidById(@PathVariable Long id) {
        return ResponseEntity.ok(bidService.getBidById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BidDto> updateBid(@PathVariable Long id, @RequestBody BidDto bidDto) {
        return ResponseEntity.ok(bidService.updateBid(id, bidDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBid(@PathVariable Long id) {
        bidService.deleteBid(id);
        return ResponseEntity.ok("Bid deleted successfully!");
    }
}
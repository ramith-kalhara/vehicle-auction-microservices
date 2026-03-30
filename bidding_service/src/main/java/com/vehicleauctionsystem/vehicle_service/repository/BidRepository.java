package com.vehicleauctionsystem.bidding_service.repository;

import com.vehicleauctionsystem.bidding_service.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByVehicleIdOrderByBidAmountDesc(Long vehicleId);

    Optional<Bid> findTopByVehicleIdOrderByBidAmountDesc(Long vehicleId);

    Optional<Bid> findTopByVehicleIdAndIdNotOrderByBidAmountDesc(Long vehicleId, Long id);
}
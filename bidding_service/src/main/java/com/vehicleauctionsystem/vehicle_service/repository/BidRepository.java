package com.vehicleauctionsystem.bidding_service.repository;

import com.vehicleauctionsystem.bidding_service.entity.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends MongoRepository<Bid, String> {

    List<Bid> findByVehicleIdOrderByBidAmountDesc(Long vehicleId);

    Optional<Bid> findTopByVehicleIdOrderByBidAmountDesc(Long vehicleId);

    Optional<Bid> findTopByVehicleIdAndIdNotOrderByBidAmountDesc(Long vehicleId, String id);
}
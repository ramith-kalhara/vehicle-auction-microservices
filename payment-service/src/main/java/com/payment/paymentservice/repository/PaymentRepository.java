package com.payment.paymentservice.repository;

import com.payment.paymentservice.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByPayerEmail(String email);
}

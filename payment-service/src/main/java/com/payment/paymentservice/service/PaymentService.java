package com.payment.paymentservice.service;

import com.payment.paymentservice.entity.Payment;
import com.payment.paymentservice.exception.PaymentNotFoundException;
import com.payment.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // CREATE
    public Payment createPayment(String payerName,
                                 String payerEmail,
                                 Double amount,
                                 String currency,
                                 MultipartFile receipt) throws IOException {

        // Convert receipt to Base64
        String base64Receipt = Base64.getEncoder()
                .encodeToString(receipt.getBytes());

        Payment payment = Payment.builder()
                .payerName(payerName)
                .payerEmail(payerEmail)
                .amount(amount)
                .currency(currency)
                .receiptBase64(base64Receipt)
                .receiptFileName(receipt.getOriginalFilename())
                .receiptContentType(receipt.getContentType())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return paymentRepository.save(payment);
    }

    // GET ALL
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // GET BY ID
    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    // UPDATE
    public Payment updatePayment(String id,
                                 String payerName,
                                 String payerEmail,
                                 Double amount,
                                 String currency,
                                 MultipartFile receipt) throws IOException {

        Payment existing = getPaymentById(id);

        if (payerName != null) existing.setPayerName(payerName);
        if (payerEmail != null) existing.setPayerEmail(payerEmail);
        if (amount != null) existing.setAmount(amount);
        if (currency != null) existing.setCurrency(currency);

        // Update receipt only if a new one is provided
        if (receipt != null && !receipt.isEmpty()) {
            String base64Receipt = Base64.getEncoder()
                    .encodeToString(receipt.getBytes());
            existing.setReceiptBase64(base64Receipt);
            existing.setReceiptFileName(receipt.getOriginalFilename());
            existing.setReceiptContentType(receipt.getContentType());
        }

        existing.setUpdatedAt(LocalDateTime.now());
        return paymentRepository.save(existing);
    }

    // DELETE
    public void deletePayment(String id) {
        paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        paymentRepository.deleteById(id);
    }
}

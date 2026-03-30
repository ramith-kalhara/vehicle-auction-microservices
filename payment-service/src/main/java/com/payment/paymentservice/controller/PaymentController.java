package com.payment.paymentservice.controller;

import com.payment.paymentservice.entity.Payment;
import com.payment.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // CREATE
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Payment> createPayment(
            @RequestParam String payerName,
            @RequestParam String payerEmail,
            @RequestParam Double amount,
            @RequestParam String currency,
            @RequestParam("receipt") MultipartFile receipt) throws IOException {

        Payment payment = paymentService.createPayment(
                payerName, payerEmail, amount, currency, receipt);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable String id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    // UPDATE
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Payment> updatePayment(
            @PathVariable String id,
            @RequestParam(required = false) String payerName,
            @RequestParam(required = false) String payerEmail,
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) String currency,
            @RequestParam(value = "receipt", required = false) MultipartFile receipt) throws IOException {

        Payment updated = paymentService.updatePayment(
                id, payerName, payerEmail, amount, currency, receipt);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable String id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}

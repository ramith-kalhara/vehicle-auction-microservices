package com.payment.paymentservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    private String id;

    // Payer Details
    private String payerName;
    private String payerEmail;

    // Amount & Currency
    private Double amount;
    private String currency;

    // Receipt stored as Base64
    private String receiptBase64;        // Base64 encoded image
    private String receiptFileName;
    private String receiptContentType;   // e.g. image/png, image/jpeg

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
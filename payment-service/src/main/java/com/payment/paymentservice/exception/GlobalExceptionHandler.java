package com.payment.paymentservice.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - Payment Not Found
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePaymentNotFound(
            PaymentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        404,
                        "Not Found",
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }

    // 400 - Missing required request param
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(
            MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        400,
                        "Bad Request",
                        "Missing required parameter: " + ex.getParameterName(),
                        LocalDateTime.now()
                ));
    }

    // 400 - File too large
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUploadSize(
            MaxUploadSizeExceededException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        400,
                        "Bad Request",
                        "Receipt file size exceeds the maximum allowed limit (10MB)",
                        LocalDateTime.now()
                ));
    }

    // 500 - Generic / Unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        500,
                        "Internal Server Error",
                        "An unexpected error occurred: " + ex.getMessage(),
                        LocalDateTime.now()
                ));
    }
}
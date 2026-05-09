package com.nhatsinh.apartmentmanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.nhatsinh.apartmentmanagement.dto.PaymentRequest;
import com.nhatsinh.apartmentmanagement.dto.PaymentResponse;
import com.nhatsinh.apartmentmanagement.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Payments", description = "Quản lý thanh toán")
@RestController
@RequestMapping("/api/payments")
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Tạo thanh toán")
    @PostMapping
    public ResponseEntity<PaymentResponse> create(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse payment = paymentService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Danh sách thanh toán")
    @GetMapping
    public ResponseEntity<Page<PaymentResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, Math.min(size, 100));

        Page<PaymentResponse> payments = paymentService.findAll(pageable);

        return ResponseEntity.ok(payments);
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Chi tiết thanh toán")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> findById(
            @PathVariable Long id) {

        PaymentResponse payment = paymentService.findById(id);

        return ResponseEntity.ok(payment);
    
    }
    @Operation(summary = "Danh sách thanh toán của tôi")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my")
    public ResponseEntity<Page<PaymentResponse>> myPayments(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
    
        Pageable pageable = PageRequest.of(page, size);
    
        return ResponseEntity.ok(
                paymentService.findMyPayments(userDetails.getUsername(),pageable)
        );
    }
    @Operation(summary = "Chi tiết thanh toán của tôi")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my/{id}")
    public ResponseEntity<PaymentResponse> myPaymentDetail(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetails userDetails) {

    return ResponseEntity.ok(
            paymentService.findMyPaymentsById(id,userDetails.getUsername())
    );
}
}
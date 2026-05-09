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

import com.nhatsinh.apartmentmanagement.dto.InvoiceRequest;
import com.nhatsinh.apartmentmanagement.dto.InvoiceResponse;
import com.nhatsinh.apartmentmanagement.dto.InvoiceUpdateRequest;
import com.nhatsinh.apartmentmanagement.service.InvoiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Invoices")
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Tạo hóa đơn")
    @PostMapping
    public ResponseEntity<InvoiceResponse> create(
            @Valid @RequestBody InvoiceRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(invoiceService.create(request));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Danh sách hóa đơn")
    @GetMapping
    public ResponseEntity<Page<InvoiceResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, Math.min(size, 100));

        return ResponseEntity.ok(invoiceService.findAll(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Chi tiết hóa đơn")
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(invoiceService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Cập nhật hóa đơn")
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody InvoiceUpdateRequest request) {

        return ResponseEntity.ok(invoiceService.update(id, request));
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Danh sách hóa đơn của tôi")
    @GetMapping("/my")
    public ResponseEntity<Page<InvoiceResponse>> myInvoices(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, Math.min(size, 100));

        return ResponseEntity.ok(
                invoiceService.findMyInvoices(
                        userDetails.getUsername(),
                        pageable
                )
        );
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Chi tiết hóa đơn của tôi")
    @GetMapping("/my/{id}")
    public ResponseEntity<InvoiceResponse> myInvoiceDetail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(
                invoiceService.findMyInvoiceById(
                        id,
                        userDetails.getUsername()
                )
        );
    }
}
package com.nhatsinh.apartmentmanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nhatsinh.apartmentmanagement.dto.ApartmentRequest;
import com.nhatsinh.apartmentmanagement.dto.ApartmentResponse;
import com.nhatsinh.apartmentmanagement.dto.ApartmentUpdateRequest;
import com.nhatsinh.apartmentmanagement.service.ApartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Apartments", description = "CRUD Apartment")
@RestController
@RequestMapping("/api/apartments")
@SecurityRequirement(name = "bearerAuth")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Tạo căn hộ")
    @PostMapping
    public ResponseEntity<ApartmentResponse> create(
            @Valid @RequestBody ApartmentRequest request) {

        ApartmentResponse apartment = apartmentService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(apartment);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @Operation(summary = "Danh sách căn hộ")
    @GetMapping
    public ResponseEntity<Page<ApartmentResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, Math.min(size, 100));

        Page<ApartmentResponse> apartments = apartmentService.findAll(pageable);

        return ResponseEntity.ok(apartments);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @Operation(summary = "Chi tiết căn hộ")
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentResponse> findById(
            @PathVariable Long id) {

        ApartmentResponse apartment = apartmentService.findById(id);

        return ResponseEntity.ok(apartment);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Cập nhật căn hộ")
    @PutMapping("/{id}")
    public ResponseEntity<ApartmentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ApartmentUpdateRequest request) {

        ApartmentResponse apartment = apartmentService.update(id, request);

        return ResponseEntity.ok(apartment);
    }
}
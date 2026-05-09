package com.nhatsinh.apartmentmanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhatsinh.apartmentmanagement.dto.ResidentRequest;
import com.nhatsinh.apartmentmanagement.dto.ResidentResponse;
import com.nhatsinh.apartmentmanagement.dto.ResidentUpdate;
import com.nhatsinh.apartmentmanagement.service.ResidentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/residents")
@Tag(name = "Residents", description = "CRUD Resident")
@SecurityRequirement(name = "bearerAuth")
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Tạo cư dân")
    @PostMapping
    public ResponseEntity<ResidentResponse> create(
            @Valid @RequestBody ResidentRequest request) {

        ResidentResponse resident = residentService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(resident);
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Danh sách cư dân")
    @GetMapping
    public ResponseEntity<Page<ResidentResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, Math.min(size, 100));

        Page<ResidentResponse> residents = residentService.findAll(pageable);

        return ResponseEntity.ok(residents);
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Chi tiết cư dân")
    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponse> findById(
            @PathVariable Long id) {

        ResidentResponse resident = residentService.findById(id);

        return ResponseEntity.ok(resident);
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Thông tin cư dân của tôi")
    @GetMapping("/me")
    public ResponseEntity<ResidentResponse> me(
    @AuthenticationPrincipal UserDetails userDetails) {

    ResidentResponse resident =
            residentService.findMyResident(
                    userDetails.getUsername()
            );

    return ResponseEntity.ok(resident);
}
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Cập nhật cư dân")
    @PutMapping("/{id}")
    public ResponseEntity<ResidentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ResidentUpdate request) {

        ResidentResponse resident = residentService.update(id, request);

        return ResponseEntity.ok(resident);
    }

    // @Operation(summary = "Xóa cư dân")
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> delete(
    //         @PathVariable Long id) {

    //     residentService.delete(id);

    //     return ResponseEntity.noContent().build();
    // }
}
package com.nhatsinh.apartmentmanagement.controller;

import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.nhatsinh.apartmentmanagement.dto.NotificationRequest;
import com.nhatsinh.apartmentmanagement.dto.NotificationResponse;
import com.nhatsinh.apartmentmanagement.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Notifications")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Tạo thông báo")
    @PostMapping
    public ResponseEntity<NotificationResponse> create(
            @Valid @RequestBody NotificationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificationService.create(request));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Danh sách thông báo")
    @GetMapping
    public ResponseEntity<Page<NotificationResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, Math.min(size, 100));

        return ResponseEntity.ok(notificationService.findAll(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @Operation(summary = "Chi tiết thông báo")
    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(notificationService.findById(id));
    }

}
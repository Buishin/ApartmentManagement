package com.nhatsinh.apartmentmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhatsinh.apartmentmanagement.dto.AuthReponse;
import com.nhatsinh.apartmentmanagement.dto.LoginRequest;
import com.nhatsinh.apartmentmanagement.dto.RegisterRequest;
import com.nhatsinh.apartmentmanagement.dto.UserResponse;
import com.nhatsinh.apartmentmanagement.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Auth", description = "Đăng ký, đăng nhập, thông tin user hiện tại")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Đăng ký user mới")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse created = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Đăng nhập, nhập JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthReponse> login(@Valid @RequestBody LoginRequest request) {
        AuthReponse auth = authService.login(request);
        return ResponseEntity.ok(auth);
    }

    @Operation(summary = "Thông tin user hiện tại (cần JWT)")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserResponse user = authService.getCurrentUser(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }
}

package com.nhatsinh.apartmentmanagement.dto;

import java.time.Instant;

import com.nhatsinh.apartmentmanagement.entity.Role;

public record UserResponse(
    Long id,
    String username,
    String email,
    Role role,
    String fullName,
    Instant createdAt,
    Instant updatedAt
) {

}

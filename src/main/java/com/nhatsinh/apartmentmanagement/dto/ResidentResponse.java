package com.nhatsinh.apartmentmanagement.dto;

public record ResidentResponse(
    Long id,
    String fullName,
    String phone,
    Long userId,
    String username,
    Long apartmentId
) {}

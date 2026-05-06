package com.nhatsinh.apartmentmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public record ResidentUpdate(
    @NotBlank(message = "Họ tên không được để trống")
    String fullName,

    @NotBlank(message = "Số điện thoại không được để trống")
    String phone
) {
    
}

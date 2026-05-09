package com.nhatsinh.apartmentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ApartmentUpdateRequest(
    @NotBlank(message="mã căn hộ không được để trống")
    @Size(max = 10)
    String number,

    @NotNull(message="diện tích không được để trống")
    Double area,

    @NotBlank(message="trạng thái không được để trống")
    String status) {
    
}

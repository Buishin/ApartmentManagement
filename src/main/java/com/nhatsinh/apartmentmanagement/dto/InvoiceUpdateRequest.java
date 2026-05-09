package com.nhatsinh.apartmentmanagement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record InvoiceUpdateRequest(
        @NotBlank(message="số tiền không được để trống")
    Double amount,

    @NotBlank(message = "trạng thái không được để trống")
    String status,

    @NotBlank(message="ngày đáo hạn không được để trống")
    LocalDate dueDate
)
{}

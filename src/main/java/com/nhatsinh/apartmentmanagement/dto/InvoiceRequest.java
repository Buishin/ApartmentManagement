package com.nhatsinh.apartmentmanagement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InvoiceRequest(
    @NotNull(message="số tiền không được để trống")
    Double amount,

    @NotBlank(message = "trạng thái không được để trống")
    String status,

    @NotNull(message="ngày đáo hạn không được để trống")
    LocalDate dueDate,

    @NotNull(message="resident_id không được trống")
    long resident_id
) {

}

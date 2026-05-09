package com.nhatsinh.apartmentmanagement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
    @NotNull(message ="giá tiền không được để trống")
    Double amount,

    @NotBlank(message="method không được trống")
    String  method,

    @NotNull(message="ngày thanh toán không được trống")
    LocalDate paymentDate,

    @NotNull(message="Invoice id không được trống")
    long invoice_id

) {

}

package com.nhatsinh.apartmentmanagement.dto;

import java.time.LocalDate;

public record PaymentResponse(
    long id,
    Double amount,
    String method,
    LocalDate paymentDate,
    long invoice_id
) {

}

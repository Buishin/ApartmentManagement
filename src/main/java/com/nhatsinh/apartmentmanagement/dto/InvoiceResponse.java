package com.nhatsinh.apartmentmanagement.dto;

import java.time.LocalDate;

public record InvoiceResponse(
    long id,
    double amount,
    String status,
    LocalDate dueDate,
    long resident_id
) {

}

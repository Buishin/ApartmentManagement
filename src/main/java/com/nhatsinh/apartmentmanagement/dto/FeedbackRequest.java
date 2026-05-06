package com.nhatsinh.apartmentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FeedbackRequest(
    @NotBlank(message="contenx không được null")
    String contenx,

    @NotBlank(message="status không được null")
    String status,

    @NotNull(message="resident id không được null")
    long resident_id
) {

}

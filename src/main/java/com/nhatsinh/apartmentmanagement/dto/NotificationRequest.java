package com.nhatsinh.apartmentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationRequest(
    @NotBlank(message="title không được trống")
    String title,

    @NotBlank(message="Content không được để trống")
    String content,

    @NotNull(message="user id không để trống")
    long user_id
) {

}

package com.nhatsinh.apartmentmanagement.dto;

import java.time.LocalDateTime;

public record NotificationResponse(
    long id,
    String title,
    String content,
    LocalDateTime createdAt,
    long user_id
    ) {

}

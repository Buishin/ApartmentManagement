package com.nhatsinh.apartmentmanagement.dto;

public record FeedbackResponse(
    long id,
    String content,
    String status,
    long resident_id

) {

}

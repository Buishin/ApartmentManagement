package com.nhatsinh.apartmentmanagement.service;

import org.springframework.stereotype.Component;

import com.nhatsinh.apartmentmanagement.dto.NotificationResponse;
import com.nhatsinh.apartmentmanagement.entity.Notification;

@Component
public class NotificationMapper {

    public NotificationResponse toResponse(Notification notification) {
        if (notification == null) return null;

        return new NotificationResponse(
                notification.getId(),
                notification.getTitle(),
                notification.getContent(),
                notification.getCreatedAt(),
                notification.getUser().getId()
        );
    }
}

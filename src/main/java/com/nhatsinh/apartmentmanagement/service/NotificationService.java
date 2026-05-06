package com.nhatsinh.apartmentmanagement.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhatsinh.apartmentmanagement.dto.NotificationRequest;
import com.nhatsinh.apartmentmanagement.dto.NotificationResponse;
import com.nhatsinh.apartmentmanagement.entity.Notification;
import com.nhatsinh.apartmentmanagement.entity.User;
import com.nhatsinh.apartmentmanagement.exception.ResourceNotFoundException;
import com.nhatsinh.apartmentmanagement.repository.NotificationRepository;
import com.nhatsinh.apartmentmanagement.repository.UserRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(
            NotificationRepository notificationRepository,
            UserRepository userRepository,
            NotificationMapper notificationMapper) {

        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.notificationMapper = notificationMapper;
    }

    @Transactional
    public NotificationResponse create(NotificationRequest request) {
        User user = userRepository.findById(request.user_id())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resident", request.user_id()));

        Notification notification = Notification.builder()
                .title(request.title())
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        notification = notificationRepository.save(notification);

        return notificationMapper.toResponse(notification);
    }

    @Transactional(readOnly = true)
    public Page<NotificationResponse> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notificationMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public NotificationResponse findById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification", id));

        return notificationMapper.toResponse(notification);
    }
}
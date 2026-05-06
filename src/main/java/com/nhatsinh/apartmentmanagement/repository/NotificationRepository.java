package com.nhatsinh.apartmentmanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Notification;

public interface  NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findByUserUsername(
        String username,
        Pageable pageable
);

    Optional<Notification> findByIdAndUserUsername(
        Long id,
        String username
);
}

package com.nhatsinh.apartmentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Notification;

public interface  NotificationRepository extends JpaRepository<Notification, Long> {

}

package com.nhatsinh.apartmentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Feedback;

public interface  FeedbackRepository extends JpaRepository<Feedback, Long> {

}

package com.nhatsinh.apartmentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Payment;

public interface  PaymentRepository extends JpaRepository<Payment, Long> {

}

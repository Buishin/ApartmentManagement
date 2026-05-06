package com.nhatsinh.apartmentmanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Page<Payment> findByInvoiceResidentUserUsername(
            String username,
            Pageable pageable
    );

    Optional<Payment> findByIdAndInvoiceResidentUserUsername(
            Long id,
            String username
    );
}
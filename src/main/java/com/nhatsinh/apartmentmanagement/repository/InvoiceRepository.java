package com.nhatsinh.apartmentmanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Invoice;

public interface  InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findByResidentUserUsername(
        String username,
        Pageable pageable
);

Optional<Invoice> findByIdAndResidentUserUsername(
        Long id,
        String username
);
}

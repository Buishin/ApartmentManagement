package com.nhatsinh.apartmentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    Optional<Resident> findByUserUsername(String username);
}
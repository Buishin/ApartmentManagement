package com.nhatsinh.apartmentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
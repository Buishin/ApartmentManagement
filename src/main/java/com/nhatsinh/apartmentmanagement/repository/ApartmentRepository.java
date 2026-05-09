package com.nhatsinh.apartmentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
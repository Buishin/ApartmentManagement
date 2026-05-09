package com.nhatsinh.apartmentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhatsinh.apartmentmanagement.entity.Service;

public interface  ServiceRequestRepository extends JpaRepository<Service, Long> {

}

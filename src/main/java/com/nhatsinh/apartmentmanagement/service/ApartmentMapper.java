package com.nhatsinh.apartmentmanagement.service;

import org.springframework.stereotype.Component;

import com.nhatsinh.apartmentmanagement.dto.ApartmentResponse;
import com.nhatsinh.apartmentmanagement.entity.Apartment;

@Component
public class ApartmentMapper {
    public ApartmentResponse toResponse(Apartment apartment){
        if(apartment==null) return null;
        return new ApartmentResponse(
            apartment.getId(),
            apartment.getNumber(),
            apartment.getArea(),
            apartment.getStatus()
        );
    }
}

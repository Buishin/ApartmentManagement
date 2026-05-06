package com.nhatsinh.apartmentmanagement.service;

import org.springframework.stereotype.Component;

import com.nhatsinh.apartmentmanagement.dto.ResidentResponse;
import com.nhatsinh.apartmentmanagement.entity.Resident;

@Component
public class ResidentMapper {
    public ResidentResponse toResponse(Resident resident){
        if(resident == null) return null;

        return new ResidentResponse(
            resident.getId(),
            resident.getFullName(),
            resident.getPhone(),
            resident.getUser().getId(),
            resident.getUser().getUsername(),
            resident.getApartment().getId()

        );
    }   
}

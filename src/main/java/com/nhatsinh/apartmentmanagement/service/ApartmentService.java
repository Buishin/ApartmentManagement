package com.nhatsinh.apartmentmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhatsinh.apartmentmanagement.dto.ApartmentRequest;
import com.nhatsinh.apartmentmanagement.dto.ApartmentResponse;
import com.nhatsinh.apartmentmanagement.dto.ApartmentUpdateRequest;
import com.nhatsinh.apartmentmanagement.entity.Apartment;
import com.nhatsinh.apartmentmanagement.exception.ResourceNotFoundException;
import com.nhatsinh.apartmentmanagement.repository.ApartmentRepository;


@Service
public class ApartmentService {
    public final ApartmentRepository apartmentRepository;
    public final ApartmentMapper apartmentMapper;

    public ApartmentService(ApartmentRepository apartmentRepository , ApartmentMapper apartmentMapper){
         this.apartmentRepository = apartmentRepository;
         this.apartmentMapper = apartmentMapper;
       
    }

    @Transactional
    public ApartmentResponse create(ApartmentRequest request){
        Apartment apartment = Apartment.builder()
        .number(request.number())
        .area(request.area())
        .status(request.status())
        .build();
        
        apartment = apartmentRepository.save(apartment);

        return apartmentMapper.toResponse(apartment);

    }
    @Transactional(readOnly = true)
    public Page<ApartmentResponse> findAll(Pageable pageable) {
        return apartmentRepository.findAll(pageable)
                .map(apartmentMapper::toResponse);
    }
    @Transactional(readOnly = true)
    public ApartmentResponse findById(Long id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apartment", id));

        return apartmentMapper.toResponse(apartment);
    }
    @Transactional
    public ApartmentResponse update(Long id, ApartmentUpdateRequest request) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apartment", id));
        
        apartment.setNumber(request.number());
        apartment.setArea(request.area());
        apartment.setStatus(request.status());

        apartment = apartmentRepository.save(apartment);

        return apartmentMapper.toResponse(apartment);
    }


}

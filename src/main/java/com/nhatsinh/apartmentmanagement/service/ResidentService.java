package com.nhatsinh.apartmentmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhatsinh.apartmentmanagement.dto.ResidentRequest;
import com.nhatsinh.apartmentmanagement.dto.ResidentResponse;
import com.nhatsinh.apartmentmanagement.dto.ResidentUpdate;
import com.nhatsinh.apartmentmanagement.entity.Apartment;
import com.nhatsinh.apartmentmanagement.entity.Resident;
import com.nhatsinh.apartmentmanagement.entity.User;
import com.nhatsinh.apartmentmanagement.exception.ResourceNotFoundException;
import com.nhatsinh.apartmentmanagement.repository.ApartmentRepository;
import com.nhatsinh.apartmentmanagement.repository.ResidentRepository;
import com.nhatsinh.apartmentmanagement.repository.UserRepository;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;
    private final ResidentMapper residentMapper;

    public ResidentService(
            ResidentRepository residentRepository,
            UserRepository userRepository,
            ApartmentRepository apartmentRepository,
            ResidentMapper residentMapper) {
        this.residentRepository = residentRepository;
        this.userRepository = userRepository;
        this.apartmentRepository = apartmentRepository;
        this.residentMapper = residentMapper;
    }
      @Transactional
    public ResidentResponse create(ResidentRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", request.userId()));

        Apartment apartment = apartmentRepository.findById(request.apartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Apartment", request.apartmentId()));

        Resident resident = Resident.builder()
                .fullName(request.fullname())
                .phone(request.phone())
                .user(user)
                .apartment(apartment)
                .build();

        resident = residentRepository.save(resident);

        return residentMapper.toResponse(resident);
    }

    @Transactional(readOnly = true)
    public Page<ResidentResponse> findAll(Pageable pageable) {
        Page<Resident> residents = residentRepository.findAll(pageable);

        return residents.map(residentMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public ResidentResponse findById(Long id) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident", id));

        return residentMapper.toResponse(resident);
    }

    @Transactional
    public ResidentResponse update(Long id, ResidentUpdate request) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident", id));

        resident.setFullName(request.fullName());
        resident.setPhone(request.phone());

        resident = residentRepository.save(resident);

        return residentMapper.toResponse(resident);
    }
    @Transactional(readOnly = true)
    public ResidentResponse findMyResident(String username) {
    Resident resident = residentRepository
            .findByUserUsername(username)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Resident"));

    return residentMapper.toResponse(resident);
}

    // @Transactional
    // public void delete(Long id) {
    //     Resident resident = residentRepository.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("Resident", id));

    //     residentRepository.delete(resident);
    // }
}
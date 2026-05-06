package com.nhatsinh.apartmentmanagement.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhatsinh.apartmentmanagement.dto.UserResponse;
import com.nhatsinh.apartmentmanagement.entity.Role;
import com.nhatsinh.apartmentmanagement.entity.User;
import com.nhatsinh.apartmentmanagement.exception.ResourceNotFoundException;
import com.nhatsinh.apartmentmanagement.repository.UserRepository;

@Service
public class UserService {
   private static final Logger log = LoggerFactory.getLogger(Service.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> findAll(String search, Role role, Pageable pageable) {
        Page<User> users = userRepository.findAllBySearchAndRole(search, role, pageable);

        return users.map(userMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User", id));

        return userMapper.toResponse(user);
    }
    
}


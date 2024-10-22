package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import com.team5.techradar.model.dto.UserResponse;
import com.team5.techradar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final SpecializationService specializationService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponse getCurrentUser() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        log.info("Getting user information from the context: {}", email);
        return mapper.map(findUserByEmail(email), UserResponse.class);
    }

    @Transactional
    public void createUser(UserRegistrationRequest request) {
        log.info("Creating new user: {}", request.getEmail());
        User user = mapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Specialization specialization = specializationService.getSpecializationById(request.getSpecializationId());
        user.setSpecialization(specialization);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    protected User findUserByEmail(String email) {
        log.info("Finding user by email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));
    }

    @Transactional(readOnly = true)
    public boolean isUserExist(String email) {
        log.info("Checking if user with email {} exists", email);
        return userRepository.existsByEmail(email);
    }
}

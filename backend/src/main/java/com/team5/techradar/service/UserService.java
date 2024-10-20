package com.team5.techradar.service;

import com.team5.techradar.model.Role;
import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import com.team5.techradar.model.dto.UserResponse;
import com.team5.techradar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public UserResponse getCurrentUser() {
        User user = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        log.info("Getting user information from the context: {}", user.getEmail());
        UserResponse response = mapper.map(user, UserResponse.class);
        response.setAdmin(user.getRole() == Role.ROLE_ADMIN);
        response.setSpecialization(user.getSpecialization().getName());
        return response;
    }

    @Transactional
    public void createUser(UserRegistrationRequest request) {
        log.info("Creating new user: {}", request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Specialization specialization = specializationService.getSpecializationById(request.getSpecializationId());
        user.setSpecialization(specialization);

        userRepository.save(user);
    }
}

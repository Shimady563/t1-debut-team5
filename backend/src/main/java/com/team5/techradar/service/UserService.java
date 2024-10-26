package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.Technology;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import com.team5.techradar.model.dto.UserResponse;
import com.team5.techradar.model.dto.UserTechnologyResponse;
import com.team5.techradar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final SpecializationService specializationService;
    private final TechnologyService technologyService;
    private final PasswordEncoder passwordEncoder;

    public String getUserEmail() {
        return (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @Transactional(readOnly = true)
    public UserResponse getCurrentUser() {
        String email = getUserEmail();
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

    @Transactional
    public UserTechnologyResponse getCurrentUserFetchTechnologies() {
        String email = getUserEmail();
        log.info("Getting user by email with technologies: {}", email);
        User user = userRepository.findFetchTechnologiesByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));
        return mapper.map(user, UserTechnologyResponse.class);
    }

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

    @Transactional
    public void modifyTechnologies(List<Long> technologyIds) {
        log.info("Adding technologies to the current user, technology ids: {}", technologyIds);
        String email = getUserEmail();
        User user = findUserByEmail(email);
        List<Technology> technologies = technologyService.getAllTechnologiesByIds(technologyIds);
        user.setTechnologies(
                user.getTechnologies().stream()
                        .filter(technologies::contains)
                        .collect(Collectors.toSet())
        );
        technologies.forEach(user::addTechnology);
        userRepository.save(user);
    }

    @Transactional
    public void removeTechnologies(List<Long> technologyIds) {
        log.info("Removing technologies from current user, technology ids: {}", technologyIds);
        String email = getUserEmail();
        User user = findUserByEmail(email);
        List<Technology> technologies = technologyService.getAllTechnologiesByIds(technologyIds);
        technologies.forEach(user::removeTechnology);
        userRepository.save(user);
    }
}

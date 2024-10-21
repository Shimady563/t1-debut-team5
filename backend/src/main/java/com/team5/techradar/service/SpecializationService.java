package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.dto.SpecializationResponse;
import com.team5.techradar.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final ModelMapper mapper;

    @Transactional
    protected Specialization getSpecializationById(Long id) {
        log.info("Getting specialization by id {}", id);
        return specializationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization with id " + id + " not found"));
    }

    @Transactional
    public List<SpecializationResponse> getAllSpecializations() {
        log.info("Getting all specializations");
        return specializationRepository.findAll().stream()
                .map(o -> mapper.map(o, SpecializationResponse.class))
                .collect(Collectors.toList());
    }
}

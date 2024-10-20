package com.team5.techradar.service;

import com.team5.techradar.model.dto.TechnologyResponse;
import com.team5.techradar.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final ModelMapper mapper;

    @Transactional
    public List<TechnologyResponse> getAllTechnologies() {
        return technologyRepository.findAll().stream()
                .map(o -> mapper.map(o, TechnologyResponse.class))
                .toList();
    }

    @Transactional
    public List<TechnologyResponse> getAllTechnologiesByActivity(Boolean isActive) {
        return technologyRepository.findAllByIsActive(isActive).stream()
                .map(o -> mapper.map(o, TechnologyResponse.class))
                .toList();
    }


}

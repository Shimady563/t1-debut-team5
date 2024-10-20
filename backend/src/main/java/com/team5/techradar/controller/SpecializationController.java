package com.team5.techradar.controller;

import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.dto.SpecializationResponse;
import com.team5.techradar.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/specializations")
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping("")
    public List<SpecializationResponse> getAllSpecializations() {
        return specializationService.getAllSpecializations();
    }
}

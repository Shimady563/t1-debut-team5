package com.team5.techradar.controller;

import com.team5.techradar.model.dto.SpecializationResponse;
import com.team5.techradar.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specializations")
@RequiredArgsConstructor
@Secured("ROLE_ADMIN")
public class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping("")
    public List<SpecializationResponse> getAllSpecializations() {
        return specializationService.getAllSpecializations();
    }
}

package com.team5.techradar.controller;

import com.team5.techradar.model.dto.TechnologyResponse;
import com.team5.techradar.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/technologies")
@RequiredArgsConstructor
public class TechnologyController {

    private final TechnologyService technologyService;

    @GetMapping("")
    public List<TechnologyResponse> getAllTechnologies() {
        return technologyService.getAllTechnologies();
    }

    @GetMapping("/active")
    public List<TechnologyResponse> getAllTechnologiesByActivity(@RequestParam(name = "active") Boolean isActive) {
        return technologyService.getAllTechnologiesByActivity(isActive);
    }
}

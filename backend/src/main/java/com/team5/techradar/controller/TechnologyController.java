package com.team5.techradar.controller;

import com.team5.techradar.model.dto.TechnologyCreationRequest;
import com.team5.techradar.model.dto.TechnologyResponse;
import com.team5.techradar.model.dto.TechnologyUpdateRequest;
import com.team5.techradar.service.TechnologyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technologies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
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

    @PostMapping("")
    public void createTechnology(@Valid @RequestBody TechnologyCreationRequest request) {
        technologyService.createTechnology(request);
    }

    @PutMapping("/{id}")
    public void updateTechnology(@PathVariable Long id, @Valid @RequestBody TechnologyUpdateRequest request) {
        technologyService.updateTechnology(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTechnology(@PathVariable Long id) {
        technologyService.deleteTechnology(id);
    }
}

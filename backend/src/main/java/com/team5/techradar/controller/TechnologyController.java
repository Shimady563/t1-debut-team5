package com.team5.techradar.controller;

import com.team5.techradar.model.dto.*;
import com.team5.techradar.service.TechnologyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technologies")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT")
@Tag(name = "Technology controller",
        description = "Позволяет получить список всех технологий, создать, обновить и удалить технологии, " +
                "а также получать статистику использования и голосов")
public class TechnologyController {

    private final TechnologyService technologyService;

    @GetMapping("")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<TechnologyResponse> getAllTechnologies() {
        return technologyService.getAllTechnologies();
    }

    @GetMapping("/active")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<TechnologyResponse> getAllTechnologiesByActivity(@RequestParam(name = "active") Boolean isActive) {
        return technologyService.getAllTechnologiesByActivity(isActive);
    }


    @GetMapping("/usage-stats")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<TechnologyStatsResponse> getTechnologiesWithUsageStats() {
        return technologyService.getAllTechnologiesWithUsageStats();
    }

    @GetMapping("/vote-stats")
    @Secured("ROLE_ADMIN")
    public List<VoteStatsResponse> getTechnologiesWithVoteStats() {
        return technologyService.getAllTechnologiesWithVoteStats();
    }

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    public void createTechnology(@Valid @RequestBody TechnologyCreationRequest request) {
        technologyService.createTechnology(request);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void updateTechnology(@PathVariable Long id, @Valid @RequestBody TechnologyUpdateRequest request) {
        technologyService.updateTechnology(id, request);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteTechnology(@PathVariable Long id) {
        technologyService.deleteTechnology(id);
    }
}

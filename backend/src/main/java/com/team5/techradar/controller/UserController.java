package com.team5.techradar.controller;

import com.team5.techradar.model.dto.UserResponse;
import com.team5.techradar.model.dto.UserTechnologyResponse;
import com.team5.techradar.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Secured({"ROLE_USER", "ROLE_ADMIN"})
@SecurityRequirement(name = "JWT")
@Tag(name = "User controller", description = "Позволяет получить информацию о текущем пользователе, а также изменить его технологии")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/technology")
    public UserTechnologyResponse getCurrentUserWithTechnologies() {
        return userService.getCurrentUserFetchTechnologies();
    }

    @PutMapping("/technology")
    public void modifyTechnologies(@RequestBody List<Long> technologyIds) {
        userService.modifyTechnologies(technologyIds);
    }

    @DeleteMapping("/technology")
    public void removeTechnologies(@NotEmpty @RequestBody List<Long> technologyIds) {
        userService.removeTechnologies(technologyIds);
    }
}

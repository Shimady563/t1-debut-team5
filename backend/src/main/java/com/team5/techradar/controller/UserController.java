package com.team5.techradar.controller;

import com.team5.techradar.model.dto.UserRegistrationRequest;
import com.team5.techradar.model.dto.UserResponse;
import com.team5.techradar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getCurrentUser() {
        return userService.getCurrentUser();
    }
}

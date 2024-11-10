package com.team5.techradar.controller;


import com.team5.techradar.exception.EmailIsOccupiedException;
import com.team5.techradar.model.dto.JwtResponse;
import com.team5.techradar.model.dto.UserLoginRequest;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import com.team5.techradar.service.AuthService;
import com.team5.techradar.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody UserLoginRequest userDTO) {
        log.info("Received login request for user with email: {}", userDTO.getEmail());
        return authService.authUser(userDTO);
    }

    @PostMapping("/register")
    public JwtResponse register(@Valid @RequestBody UserRegistrationRequest userDTO) {
        log.info("Received register request for user with email: {}", userDTO.getEmail());
        if (userService.isUserExist(userDTO.getEmail())) {
            throw new EmailIsOccupiedException("Email is already occupied: " + userDTO.getEmail());
        }
        return authService.registerNewUser(userDTO);
    }
}

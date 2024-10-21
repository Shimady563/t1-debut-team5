package com.team5.techradar.controller;


import com.team5.techradar.model.dto.*;
import com.team5.techradar.security.exception.EmailIsOccupiedException;
import com.team5.techradar.service.AuthService;
import com.team5.techradar.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody UserLoginRequest userDTO) {
        log.info("Received login request for user: {}", userDTO);
        return authService.authUser(userDTO);
    }

    @PostMapping("/register")
    public JwtResponse register(@RequestBody UserRegistrationRequest userDTO) throws EmailIsOccupiedException {
        log.info("Received register request for user: {}", userDTO);
        if (!userService.isUserExist(userDTO.getEmail())) {
            return authService.registerNewUser(userDTO);
        } else {
            throw new EmailIsOccupiedException();
        }
    }
}

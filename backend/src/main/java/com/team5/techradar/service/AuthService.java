package com.team5.techradar.service;

import com.team5.techradar.model.Role;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.JwtResponse;
import com.team5.techradar.model.dto.UserLoginRequest;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public synchronized JwtResponse registerNewUser(UserRegistrationRequest request) {
        log.info("Registering user with email: {}", request.getEmail());
        userService.createUser(request);
        String token = jwtService.generateAccessToken(request.getEmail(), Role.ROLE_USER);
        return new JwtResponse(request.getEmail(), token);
    }

    public JwtResponse authUser(UserLoginRequest request) {
        log.info("Logging in user with email: {}", request.getEmail());
        User user = userService.findUserByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password for user: " + request.getEmail());
        }

        Role userRole = user.getRole();
        final String token = jwtService.generateAccessToken(request.getEmail(), userRole);
        return new JwtResponse(request.getEmail(), token);

    }
}

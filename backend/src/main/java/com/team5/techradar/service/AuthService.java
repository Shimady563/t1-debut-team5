package com.team5.techradar.service;

import com.team5.techradar.model.Role;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.JwtResponse;
import com.team5.techradar.model.dto.UserLoginRequest;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import com.team5.techradar.security.jwt.service.JwtService;
import com.team5.techradar.security.verifier.UserVerifier;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final UserVerifier userVerifier;

    public synchronized JwtResponse registerNewUser(UserRegistrationRequest user) {
        userService.createUser(user);

        return authUser(new UserLoginRequest(user.getEmail(), user.getPassword()));
    }

    public JwtResponse authUser(UserLoginRequest user) {
        if (userVerifier.verify(user)) {
            User userEntity = userService.findUserByEmail(user.getEmail());
            Role userRole = userEntity.getRole();
            final String token = jwtService.generateAccessToken(user.getEmail(), userRole);
            return new JwtResponse(user.getEmail(), token);
        }
        throw new BadCredentialsException("Invalid email or password");
    }
}

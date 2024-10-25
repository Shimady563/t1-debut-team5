package com.team5.techradar.service;

import com.team5.techradar.model.Role;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.UserLoginRequest;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    public void shouldRegisterNewUser() {
        var request = new UserRegistrationRequest();
        request.setSpecializationId(1L);
        request.setEmail("test@example.com");
        request.setPassword("password123");

        authService.registerNewUser(request);

        then(userService).should().createUser(request);
        then(jwtService).should().generateAccessToken(request.getEmail(), Role.ROLE_USER);
    }

    @Test
    public void shouldAuthenticateUser() {
        var request = new UserLoginRequest("test@example.com", "password123");
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.ROLE_USER);

        given(passwordEncoder.matches(request.getPassword(), user.getPassword())).willReturn(true);
        given(userService.findUserByEmail(request.getEmail())).willReturn(user);
        given(jwtService.generateAccessToken(request.getEmail(), user.getRole())).willReturn("fakeToken");

        authService.authUser(request);

        then(passwordEncoder).should().matches(request.getPassword(), user.getPassword());
        then(userService).should().findUserByEmail(request.getEmail());
        then(jwtService).should().generateAccessToken(request.getEmail(), user.getRole());
    }

    @Test
    public void shouldThrowExceptionWhenWrongPasswordIsInRequest() {
        UserLoginRequest request = new UserLoginRequest("wrong@example.com", "wrongPassword");
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword("password123");

        given(userService.findUserByEmail(request.getEmail())).willReturn(user);
        given(passwordEncoder.matches(request.getPassword(), user.getPassword())).willReturn(false);

        assertThrows(BadCredentialsException.class,
                () -> authService.authUser(request));
    }
}

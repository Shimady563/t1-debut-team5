package com.team5.techradar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.techradar.config.TestConfig;
import com.team5.techradar.model.dto.JwtResponse;
import com.team5.techradar.model.dto.UserLoginRequest;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import com.team5.techradar.service.AuthService;
import com.team5.techradar.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(TestConfig.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserService userService;

    @Test
    @WithAnonymousUser
    public void shouldLoginUser() throws Exception {
        var request = new UserLoginRequest("test@mail.com", "password123");
        var response = new JwtResponse(request.getEmail(), "fakeToken");

        given(authService.authUser(request)).willReturn(response);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void shouldRegisterUser() throws Exception {
        var request = new UserRegistrationRequest();
        request.setSpecializationId(1L);
        request.setEmail("test@email.com");
        request.setPassword("password123");
        var response = new JwtResponse(request.getEmail(), "fakeToken");

        given(authService.registerNewUser(request)).willReturn(response);
        given(userService.isUserExist(request.getEmail())).willReturn(false);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void shouldThrowAnExceptionWhenEmailIsOccupied() throws Exception {
        var request = new UserRegistrationRequest();
        request.setSpecializationId(1L);
        request.setEmail("test@email.com");
        request.setPassword("password123");

        given(userService.isUserExist(request.getEmail())).willReturn(true);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }
}

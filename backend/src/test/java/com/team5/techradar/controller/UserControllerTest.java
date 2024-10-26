package com.team5.techradar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.techradar.config.TestConfig;
import com.team5.techradar.model.dto.UserTechnologyResponse;
import com.team5.techradar.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(TestConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void shouldGetCurrentUser() throws Exception {
        mockMvc.perform(get("/users/me").with(csrf())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(userService).should().getCurrentUser();
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void shouldGetCurrentUserWithTechnologies() throws Exception {
        var response = new UserTechnologyResponse();
        response.setId(1L);
        response.setEmail("email@mail.com");
        response.setAdmin(false);
        response.setSpecialization("Specialization");
        response.setTechnologies(List.of());

        given(userService.getCurrentUserFetchTechnologies()).willReturn(response);

        mockMvc.perform(get("/users/technology").with(csrf())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(userService).should().getCurrentUserFetchTechnologies();
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void shouldModifyTechnologies() throws Exception {
        var request = List.of(1L, 2L, 3L);

        mockMvc.perform(put("/users/technology").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        then(userService).should().modifyTechnologies(request);
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void shouldRemoveTechnologies() throws Exception {
        var request = List.of(1L, 2L, 3L);

        mockMvc.perform(delete("/users/technology").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        then(userService).should().removeTechnologies(request);
    }
}

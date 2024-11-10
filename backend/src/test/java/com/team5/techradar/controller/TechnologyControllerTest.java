package com.team5.techradar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.techradar.config.TestConfig;
import com.team5.techradar.model.Level;
import com.team5.techradar.model.Type;
import com.team5.techradar.model.dto.TechnologyCreationRequest;
import com.team5.techradar.model.dto.TechnologyUpdateRequest;
import com.team5.techradar.service.TechnologyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TechnologyController.class)
@Import(TestConfig.class)
public class TechnologyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TechnologyService technologyService;

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void shouldGetAllTechnologies() throws Exception {
        mockMvc.perform(get("/technologies")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(technologyService).should().getAllTechnologies();
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void shouldGetAllTechnologiesByActivity() throws Exception {

        mockMvc.perform(get("/technologies/active")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("active", "true"))
                .andExpect(status().isOk());

        then(technologyService).should().getAllTechnologiesByActivity(true);
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void shouldGetAllTechnologiesWithUsageStats() throws Exception {
        mockMvc.perform(get("/technologies/usage-stats")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(technologyService).should().getAllTechnologiesWithUsageStats();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldCreateTechnology() throws Exception {
        var request = new TechnologyCreationRequest();
        request.setName("Redis");
        request.setType(Type.DATABASES);
        request.setLevel(Level.ADOPT);

        mockMvc.perform(post("/technologies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        then(technologyService).should().createTechnology(request);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldUpdateTechnology() throws Exception {
        var id = 1L;
        var request = new TechnologyUpdateRequest();
        request.setName("Redis");
        request.setType(Type.DATABASES);
        request.setLevel(Level.ADOPT);
        request.setMoved(0);
        request.setIsActive(true);

        mockMvc.perform(put("/technologies/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        then(technologyService).should().updateTechnology(id, request);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldDeleteTechnology() throws Exception {
        var id = 1L;

        mockMvc.perform(delete("/technologies/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(technologyService).should().deleteTechnology(id);
    }
}

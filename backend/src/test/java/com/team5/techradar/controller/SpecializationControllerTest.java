package com.team5.techradar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.techradar.config.TestConfig;
import com.team5.techradar.service.SpecializationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpecializationController.class)
@Import(TestConfig.class)
public class SpecializationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SpecializationService specializationService;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldGetAllSpecializations() throws Exception {
        mockMvc.perform(get("/specializations")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(specializationService).should().getAllSpecializations();
    }
}

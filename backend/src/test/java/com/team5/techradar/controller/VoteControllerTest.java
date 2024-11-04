package com.team5.techradar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.techradar.config.TestConfig;
import com.team5.techradar.model.Level;
import com.team5.techradar.model.dto.VoteCreationRequest;
import com.team5.techradar.service.VoteService;
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

@WebMvcTest(VoteController.class)
@Import(TestConfig.class)
public class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VoteService voteService;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldGetAllVotes() throws Exception {
        mockMvc.perform(get("/votes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(voteService).should().getAllVotes();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldGetVoteById() throws Exception {
        var id = 1L;

        mockMvc.perform(get("/votes/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(voteService).should().getVoteById(id);
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void shouldGetVotesForCurrentUser() throws Exception {
        mockMvc.perform(get("/votes/user")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(voteService).should().getVotesForCurrentUser();
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void shouldCreateVote() throws Exception {
        var request = new VoteCreationRequest();
        request.setLevel(Level.ADOPT);
        request.setTechnologyId(1L);

        mockMvc.perform(post("/votes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        then(voteService).should().createVote(request);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldGetAllVotesByTechnologyId() throws Exception {
        var technologyId = 1L;

        mockMvc.perform(get("/votes/technology")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("technologyId", String.valueOf(technologyId)))
                .andExpect(status().isOk());

        then(voteService).should().getAllVotesByTechnologyId(technologyId);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldDeleteVotesByTechnologyId() throws Exception {
        var technologyId = 1L;

        mockMvc.perform(delete("/votes/technology")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("technologyId", String.valueOf(technologyId)))
                .andExpect(status().isOk());

        then(voteService).should().deleteVotesByTechnologyId(technologyId);
    }
}

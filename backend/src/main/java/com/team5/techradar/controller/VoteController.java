package com.team5.techradar.controller;

import com.team5.techradar.model.dto.UserVoteResponse;
import com.team5.techradar.model.dto.VoteCreationRequest;
import com.team5.techradar.model.dto.VoteResponse;
import com.team5.techradar.service.VoteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT")
@Tag(name = "Vote controller",
        description = "Позволяет получать список всех голосов, создавать голоса, получать голоса текущего пользователя, " +
                "получать голоса по технологиям, удалять голоса")
public class VoteController {

    private final VoteService voteService;

    @GetMapping("")
    @Secured("ROLE_ADMIN")
    public List<VoteResponse> getAllVotes() {
        return voteService.getAllVotes();
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public VoteResponse getVoteById(@PathVariable Long id) {
        return voteService.getVoteById(id);
    }

    @GetMapping("/user")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<UserVoteResponse> getAllVotesByUserId() {
        return voteService.getVotesForCurrentUser();
    }

    @PostMapping("")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void createVote(@Valid @RequestBody VoteCreationRequest request) {
        voteService.createVote(request);
    }

    @GetMapping("/technology")
    @Secured("ROLE_ADMIN")
    public List<VoteResponse> getAllVotesByTechnologyId(@RequestParam Long technologyId) {
        return voteService.getAllVotesByTechnologyId(technologyId);
    }

    @DeleteMapping("/technology")
    @Secured("ROLE_ADMIN")
    public void deleteVotesByTechnologyId(@RequestParam Long technologyId) {
        voteService.deleteVotesByTechnologyId(technologyId);
    }
}

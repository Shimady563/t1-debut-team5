package com.team5.techradar.controller;

import com.team5.techradar.model.dto.VoteCreationRequest;
import com.team5.techradar.model.dto.VoteResponse;
import com.team5.techradar.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
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
}

package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.Technology;
import com.team5.techradar.model.User;
import com.team5.techradar.model.Vote;
import com.team5.techradar.model.dto.UserVoteResponse;
import com.team5.techradar.model.dto.VoteCreationRequest;
import com.team5.techradar.model.dto.VoteResponse;
import com.team5.techradar.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserService userService;
    private final TechnologyService technologyService;
    private final ModelMapper mapper;

    @Transactional(readOnly = true)
    public VoteResponse getVoteById(Long id) {
        log.info("Getting vote by id: {}", id);
        return mapper.map(
                voteRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Vote with id " + id + " not found")),
                VoteResponse.class
        );
    }

    @Transactional
    public void createVote(VoteCreationRequest request) {
        log.info("Creating vote for technology with id: {}", request.getTechnologyId());
        Vote vote = mapper.map(request, Vote.class);
        Technology technology = technologyService.getTechnologyById(request.getTechnologyId());
        User user = userService.findUserByEmail(userService.getUserEmail());
        vote.setUser(user);
        vote.setTechnology(technology);
        voteRepository.save(vote);
    }

    @Transactional(readOnly = true)
    public List<VoteResponse> getAllVotes() {
        log.info("Getting all votes");
        return voteRepository.findAll().stream()
                .map((vote) -> mapper.map(vote, VoteResponse.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<VoteResponse> getAllVotesByTechnologyId(Long technologyId) {
        log.info("Getting votes by technology id: {}", technologyId);
        Technology technology = technologyService.getTechnologyById(technologyId);
        return voteRepository.findFetchAllByTechnology(technology).stream()
                .map((vote) -> mapper.map(vote, VoteResponse.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserVoteResponse> getVotesForCurrentUser() {
        User user = userService.findUserByEmail(userService.getUserEmail());
        log.info("Getting votes for user with email: {}", user.getEmail());
        return voteRepository.findFetchTechnologyAllByUser(user).stream()
                .map(v -> mapper.map(v, UserVoteResponse.class))
                .toList();
    }

    @Transactional
    public void deleteVotesByTechnologyId(Long technologyId) {
        log.info("Deleting votes by technology id: {}", technologyId);
        Technology technology = technologyService.getTechnologyById(technologyId);
        voteRepository.deleteByTechnology(technology);
    }
}

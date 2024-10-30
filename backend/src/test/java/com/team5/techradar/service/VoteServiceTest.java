package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.*;
import com.team5.techradar.model.dto.VoteCreationRequest;
import com.team5.techradar.model.dto.VoteResponse;
import com.team5.techradar.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private UserService userService;

    @Mock
    private TechnologyService technologyService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private VoteService voteService;

    @Test
    public void shouldGetVoteById() {
        var vote = new Vote();
        var id = 1L;
        vote.setId(id);
        vote.setLevel(Level.ADOPT);

        given(voteRepository.findById(id)).willReturn(Optional.of(vote));

        voteService.getVoteById(id);

        then(voteRepository).should().findById(id);
    }

    @Test
    public void shouldThrowExceptionWhenVoteNotFoundById() {
        var id = 1L;

        given(voteRepository.findById(id)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> voteService.getVoteById(id));
    }

    @Test
    public void shouldCreateVote() {
        var technology = new Technology();
        technology.setId(1L);
        technology.setName("name");
        technology.setType(Type.DATABASES);
        technology.setLevel(Level.ASSESS);
        var request = new VoteCreationRequest();
        request.setLevel(Level.ADOPT);
        request.setTechnologyId(technology.getId());
        var vote = new Vote();
        vote.setLevel(request.getLevel());
        vote.setTechnology(technology);
        var user = new User();
        user.setEmail("mail@mail.com");

        given(mapper.map(request, Vote.class)).willReturn(vote);
        given(technologyService.getTechnologyById(request.getTechnologyId())).willReturn(technology);
        given(userService.getUserEmail()).willReturn(user.getEmail());
        given(userService.findUserByEmail(user.getEmail())).willReturn(user);

        voteService.createVote(request);

        then(voteRepository).should().save(vote);
        assertEquals(user, vote.getUser());
        assertEquals(technology, vote.getTechnology());

    }

    @Test
    public void shouldGetAllVotes() {
        var vote1 = new Vote();
        vote1.setLevel(Level.ADOPT);
        var vote2 = new Vote();
        vote2.setLevel(Level.ASSESS);
        var response1 = new VoteResponse();
        response1.setLevel(vote1.getLevel().getValue());
        var response2 = new VoteResponse();
        response1.setLevel(vote2.getLevel().getValue());

        given(mapper.map(vote1, VoteResponse.class)).willReturn(response1);
        given(mapper.map(vote2, VoteResponse.class)).willReturn(response2);
        given(voteRepository.findAll()).willReturn(List.of(vote1, vote2));

        voteService.getAllVotes();

        then(voteRepository).should().findAll();
    }

    @Test
    public void shouldGetVotesByTechnologyId() {
        var technologyId = 1L;
        var technology = new Technology();
        technology.setId(technologyId);
        technology.setName("name");
        technology.setType(Type.DATABASES);
        technology.setLevel(Level.ASSESS);
        var vote1 = new Vote();
        vote1.setLevel(Level.ASSESS);
        var vote2 = new Vote();
        vote2.setLevel(Level.ADOPT);
        vote1.setTechnology(technology);
        vote2.setTechnology(technology);
        var response1 = new VoteResponse();
        response1.setLevel(vote1.getLevel().getValue());
        var response2 = new VoteResponse();
        response2.setLevel(vote2.getLevel().getValue());

        given(technologyService.getTechnologyById(technologyId)).willReturn(technology);
        given(mapper.map(vote1, VoteResponse.class)).willReturn(response1);
        given(mapper.map(vote2, VoteResponse.class)).willReturn(response2);
        given(voteRepository.findFetchAllByTechnology(technology)).willReturn(List.of(vote1, vote2));

        voteService.getAllVotesByTechnologyId(technologyId);

        then(voteRepository.findFetchAllByTechnology(technology));
    }

    @Test
    public void shouldDeleteVotesByTechnologyId() {
        var technologyId = 1L;
        var technology = new Technology();
        technology.setId(technologyId);
        technology.setName("name");
        technology.setType(Type.DATABASES);
        technology.setLevel(Level.ASSESS);

        given(technologyService.getTechnologyById(technologyId)).willReturn(technology);

        voteService.deleteVotesByTechnologyId(technologyId);

        then(voteRepository).should().deleteByTechnology(technology);
    }
}


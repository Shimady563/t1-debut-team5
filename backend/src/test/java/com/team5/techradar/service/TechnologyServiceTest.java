package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.Level;
import com.team5.techradar.model.Technology;
import com.team5.techradar.model.Type;
import com.team5.techradar.model.dto.TechnologyCreationRequest;
import com.team5.techradar.model.dto.TechnologyResponse;
import com.team5.techradar.model.dto.TechnologyUpdateRequest;
import com.team5.techradar.repository.TechnologyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class TechnologyServiceTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private TechnologyService technologyService;

    @Test
    public void shouldGetTechnologyById() {
        var technology = new Technology();
        technology.setId(1L);

        given(technologyRepository.findById(technology.getId())).willReturn(Optional.of(technology));

        technologyService.getTechnologyById(technology.getId());

        then(technologyRepository).should().findById(technology.getId());
    }

    @Test
    public void shouldThrowExceptionWhenTechnologyNotFoundById() {
        given(technologyRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> technologyService.getTechnologyById(1L));
    }

    @Test
    public void shouldGetAllTechnologiesByIds() {
        var technology1 = new Technology();
        technology1.setId(1L);
        var technology2 = new Technology();
        technology2.setId(2L);

        given(technologyRepository.findAllById(List.of(technology1.getId(), technology2.getId()))).willReturn(List.of(technology1, technology2));

        technologyService.getAllTechnologiesByIds(List.of(technology1.getId(), technology2.getId()));

        then(technologyRepository).should().findAllById(List.of(technology1.getId(), technology2.getId()));
    }

    @Test
    public void shouldGetAllTechnologies() {
        var technology1 = new Technology();
        var technology2 = new Technology();
        var response1 = new TechnologyResponse();
        var response2 = new TechnologyResponse();

        given(technologyRepository.findAll()).willReturn(List.of(technology1, technology2));
        given(mapper.map(technology1, TechnologyResponse.class)).willReturn(response1);
        given(mapper.map(technology2, TechnologyResponse.class)).willReturn(response2);

        technologyService.getAllTechnologies();

        then(technologyRepository).should().findAll();
    }

    @Test
    public void shouldGetAllTechnologiesByActivity() {
        var isActive = true;
        var technology1 = new Technology();
        technology1.setIsActive(isActive);
        var technology2 = new Technology();
        technology2.setIsActive(isActive);
        var response1 = new TechnologyResponse();
        var response2 = new TechnologyResponse();

        given(technologyRepository.findAllByIsActive(isActive)).willReturn(List.of(technology1, technology2));
        given(mapper.map(technology1, TechnologyResponse.class)).willReturn(response1);
        given(mapper.map(technology2, TechnologyResponse.class)).willReturn(response2);

        technologyService.getAllTechnologiesByActivity(isActive);

        then(technologyRepository).should().findAllByIsActive(isActive);
    }

    @Test
    public void shouldCreateTechnology() {
        var technology = new Technology();

        given(mapper.map(new TechnologyCreationRequest(), Technology.class)).willReturn(technology);

        technologyService.createTechnology(new TechnologyCreationRequest());

        then(technologyRepository).should().save(technology);
    }

    @Test
    public void shouldUpdateTechnology() {
        var id = 1L;
        var request = new TechnologyUpdateRequest();
        request.setName("Java");
        request.setLevel(Level.ADOPT);
        request.setType(Type.LANGUAGES);
        request.setMoved(0);
        request.setIsActive(true);
        var technology = new Technology();
        technology.setId(id);

        given(technologyRepository.findById(id)).willReturn(Optional.of(technology));

        technologyService.updateTechnology(id, request);

        then(technologyRepository).should().save(technology);
    }

    @Test
    public void shouldDeleteTechnology() {
        var technology = new Technology();
        technology.setId(1L);

        given(technologyRepository.findFetchUsersById(1L)).willReturn(Optional.of(technology));

        technologyService.deleteTechnology(1L);

        then(technologyRepository).should().delete(technology);
    }

    @Test
    public void shouldGetTechnologyWithUsersById() {
        var technology = new Technology();
        technology.setId(1L);

        given(technologyRepository.findFetchUsersById(technology.getId())).willReturn(Optional.of(technology));

        technologyService.getTechnologyByIdFetchUsers(technology.getId());

        then(technologyRepository).should().findFetchUsersById(technology.getId());
    }

    @Test
    public void shouldThrowExceptionWhenTechnologyWithUsersNotFoundById() {
        given(technologyRepository.findFetchUsersById(1L)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> technologyService.getTechnologyByIdFetchUsers(1L));
    }
}

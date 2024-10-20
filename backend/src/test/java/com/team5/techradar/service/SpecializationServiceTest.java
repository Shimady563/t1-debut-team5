package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.dto.SpecializationResponse;
import com.team5.techradar.repository.SpecializationRepository;
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
public class SpecializationServiceTest {

    @Mock
    private SpecializationRepository specializationRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SpecializationService specializationService;

    @Test
    public void shouldGetAllSpecializations() {
        var specialization1 = new Specialization();
        var specialization2 = new Specialization();
        var response1 = new SpecializationResponse();
        var response2 = new SpecializationResponse();

        given(modelMapper.map(specialization1, SpecializationResponse.class)).willReturn(response1);
        given(modelMapper.map(specialization2, SpecializationResponse.class)).willReturn(response2);
        given(specializationRepository.findAll()).willReturn(List.of(specialization1, specialization2));

        specializationService.getAllSpecializations();

        then(specializationRepository).should().findAll();
    }

    @Test
    public void shouldGetSpecializationById() {
        var specialization = new Specialization();
        var id = 1L;
        specialization.setId(id);

        given(specializationRepository.findById(id)).willReturn(Optional.of(specialization));

        specializationService.getSpecializationById(id);

        then(specializationRepository).should().findById(id);
    }

    @Test
    public void shouldThrowExceptionWhenSpecializationNotFoundById() {
        var id = 1L;
        given(specializationRepository.findById(id)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> specializationService.getSpecializationById(id));
    }
}

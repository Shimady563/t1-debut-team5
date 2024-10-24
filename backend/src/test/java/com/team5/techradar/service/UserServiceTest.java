package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.Technology;
import com.team5.techradar.model.Type;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import com.team5.techradar.model.dto.UserTechnologyResponse;
import com.team5.techradar.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private SpecializationService specializationService;

    @Mock
    private TechnologyService technologyService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Spy
    @InjectMocks
    private UserService userService;

    @Test
    public void shouldCreateUser() {
        var request = new UserRegistrationRequest();
        request.setPassword("Password");
        request.setSpecializationId(1L);

        var specialization = new Specialization();
        specialization.setId(1L);
        var user = new User();
        user.setPassword(request.getPassword());

        given(modelMapper.map(request, User.class)).willReturn(user);
        given(passwordEncoder.encode(eq(user.getPassword()))).willReturn(request.getPassword());
        given(specializationService.getSpecializationById(request.getSpecializationId())).willReturn(specialization);

        userService.createUser(request);

        then(userRepository).should().save(eq(user));
        assertEquals(specialization, user.getSpecialization());
    }

    @Test
    public void shouldGetCurrentUserWithTechnologies() {
        var email = "email@mail.com";
        var user = new User();
        user.setEmail(email);
        var response = new UserTechnologyResponse();
        response.setEmail(email);

        given(userRepository.findFetchTechnologiesByEmail(email)).willReturn(Optional.of(user));
        willReturn(email).given(userService).getUserEmail();
        given(modelMapper.map(user, UserTechnologyResponse.class)).willReturn(response);

        userService.getCurrentUserFetchTechnologies();

        then(userRepository).should().findFetchTechnologiesByEmail(email);
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        var email = "email@mail.com";

        given(userRepository.findFetchTechnologiesByEmail(email)).willReturn(Optional.empty());
        // for some reason, the opposite syntax doesn't work with spy
        willReturn(email).given(userService).getUserEmail();

        assertThrows(ResourceNotFoundException.class,
                () -> userService.getCurrentUserFetchTechnologies());
    }

    @Test
    public void shouldGetUserByEmail() {
        var user = new User();
        var email = "mail@mail.com";
        user.setEmail(email);

        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

        userService.findUserByEmail(email);

        then(userRepository).should().findByEmail(email);
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFoundByEmail() {
        var email = "mail@mail.com";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> userService.findUserByEmail(email));
    }

    @Test
    public void shouldCheckIfUserExistsByEmail() {
        var user = new User();
        var email = "email@mail.com";
        user.setEmail(email);

        given(userRepository.existsByEmail(email)).willReturn(true);

        userService.isUserExist(email);

        then(userRepository).should().existsByEmail(email);
    }

    @Test
    public void shouldAddTechnologies() {
        var technology1 = new Technology();
        technology1.setId(1L);
        technology1.setType(Type.LANGUAGES);
        Technology technology2 = new Technology();
        technology2.setId(2L);
        technology2.setType(Type.DATABASES);
        var user = new User();
        var email = "email@mail.com";

        willReturn(email).given(userService).getUserEmail();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));
        given(technologyService.getAllTechnologiesByIds(List.of(technology1.getId(), technology2.getId())))
                .willReturn(List.of(technology1, technology2));

        userService.addTechnologies(List.of(technology1.getId(), technology2.getId()));

        then(userRepository).should().save(user);
        assertThat(user.getTechnologies()).hasSize(2);
    }

    @Test
    public void shouldRemoveTechnologies() {
        var technology1 = new Technology();
        technology1.setId(1L);
        technology1.setType(Type.PLATFORMS);
        var technology2 = new Technology();
        technology2.setId(2L);
        technology2.setType(Type.DATABASES);
        var user = new User();
        var email = "email@mail.com";
        user.addTechnology(technology1);
        user.addTechnology(technology2);

        willReturn(email).given(userService).getUserEmail();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));
        given(technologyService.getAllTechnologiesByIds(List.of(technology1.getId(), technology2.getId())))
                .willReturn(List.of(technology1, technology2));

        userService.removeTechnologies(List.of(technology1.getId(), technology2.getId()));

        then(userRepository).should().save(user);
        assertThat(user.getTechnologies()).isEmpty();
    }
}

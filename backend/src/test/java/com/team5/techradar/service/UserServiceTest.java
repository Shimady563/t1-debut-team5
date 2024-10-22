package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.UserRegistrationRequest;
import com.team5.techradar.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private SpecializationService specializationService;

    @Mock
    private PasswordEncoder passwordEncoder;

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
    public void shouldGetSpecializationById() {
        var user = new User();
        var email = "mail@mail.com";
        user.setEmail(email);

        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

        userService.findUserByEmail(email);

        then(userRepository).should().findByEmail(email);
    }

    @Test
    public void shouldThrowExceptionWhenSpecializationNotFoundById() {
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
}

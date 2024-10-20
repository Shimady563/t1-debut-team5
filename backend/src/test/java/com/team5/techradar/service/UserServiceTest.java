package com.team5.techradar.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}

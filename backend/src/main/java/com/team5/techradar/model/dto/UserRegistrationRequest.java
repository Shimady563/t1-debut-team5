package com.team5.techradar.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationRequest {
    @Email
    private String email;

    @Size(min = 8, max = 255)
    private String password;

    @NotNull
    private Long specializationId;
}

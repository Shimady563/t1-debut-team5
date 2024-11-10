package com.team5.techradar.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationRequest {
    @Email
    private String email;

    @Size(min = 8, max = 255)
    private String password;

    @NotNull
    private Long specializationId;
}

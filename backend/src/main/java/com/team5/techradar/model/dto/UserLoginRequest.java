package com.team5.techradar.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequest {
    @Email
    private String email;

    @Size(min = 8, max = 255)
    private String password;
}

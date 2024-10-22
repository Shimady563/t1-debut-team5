package com.team5.techradar.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String specialization;
    private boolean isAdmin;
}

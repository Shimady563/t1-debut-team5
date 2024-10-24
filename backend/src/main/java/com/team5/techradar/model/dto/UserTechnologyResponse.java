package com.team5.techradar.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserTechnologyResponse {
    private Long id;
    private String email;
    private String specialization;
    private boolean isAdmin;
    private List<TechnologyResponse> technologies;
}
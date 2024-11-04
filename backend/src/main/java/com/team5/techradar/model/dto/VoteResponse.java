package com.team5.techradar.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteResponse {
    private Long id;
    private String level;
    private UserResponse user;
    private TechnologyResponse technology;
}

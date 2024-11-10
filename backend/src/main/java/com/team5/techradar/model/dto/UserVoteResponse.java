package com.team5.techradar.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVoteResponse {
    private Long id;
    private String level;
    private TechnologyResponse technology;
}
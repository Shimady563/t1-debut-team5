package com.team5.techradar.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TechnologyStatsResponse {
    private Long id;
    private String name;
    private Integer moved;
    private String level;
    private Integer type;
    private Boolean isActive;
    private Integer score;
}

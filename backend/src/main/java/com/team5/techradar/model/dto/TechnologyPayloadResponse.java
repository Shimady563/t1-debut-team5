package com.team5.techradar.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TechnologyPayloadResponse {
    private Long id;
    private String name;
    private String level;
    private Integer type;
}

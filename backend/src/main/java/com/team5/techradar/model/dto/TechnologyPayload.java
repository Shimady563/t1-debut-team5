package com.team5.techradar.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TechnologyPayload {
    private Long id;
    private String name;
    private String category;
    private Double usageLevel;
}

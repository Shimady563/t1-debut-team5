package com.team5.techradar.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
public class VoteStatsResponse implements Serializable {
    private TechnologyResponse technology;
    private Map<String, Long> votes;
}

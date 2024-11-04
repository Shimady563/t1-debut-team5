package com.team5.techradar.model.dto;

import com.team5.techradar.model.Level;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteCreationRequest {

    @NotNull
    private Level level;

    @NotNull
    private Long technologyId;
}

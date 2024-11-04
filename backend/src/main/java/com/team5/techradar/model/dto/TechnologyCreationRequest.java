package com.team5.techradar.model.dto;

import com.team5.techradar.model.Level;
import com.team5.techradar.model.Type;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TechnologyCreationRequest {

    @NotEmpty
    private String name;

    @NotNull
    private Level level;

    @NotNull
    private Type type;
}

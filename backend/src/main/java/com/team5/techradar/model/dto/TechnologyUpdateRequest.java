package com.team5.techradar.model.dto;

import com.team5.techradar.model.Level;
import com.team5.techradar.model.Type;
import com.team5.techradar.validation.OneOf;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TechnologyUpdateRequest {

    @NotEmpty
    private String name;

    @OneOf(values = {-1, 0, 1})
    private Integer moved;

    @NotNull
    private Level level;

    @NotNull
    private Type type;

    @NotNull
    private Boolean isActive;
}

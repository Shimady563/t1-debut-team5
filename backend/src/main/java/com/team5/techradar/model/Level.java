package com.team5.techradar.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Level {
    ADOPT("Adopt"),
    TRIAL("Trial"),
    ASSESS("Assess"),
    HOLD("Hold");

    private final String value;
}

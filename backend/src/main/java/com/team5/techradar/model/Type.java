package com.team5.techradar.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    PLATFORMS("Platforms"),
    LANGUAGES("Languages"),
    DATABASES("Databases"),
    TOOLS("Tools");

    private final String value;
}

package com.team5.techradar.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Moved {
    DOWN("Down"),
    NOT_MOVED("NotMoved"),
    UP("Up");

    private final String value;
}

package com.team5.techradar.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ROLE_USER("User"),
    ROLE_ADMIN("Admin");

    private final String value;

    @Override
    public String getAuthority() {
        return name();
    }
}

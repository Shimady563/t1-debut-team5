package com.team5.techradar.security.jwt.service;

import com.team5.techradar.model.Role;
import com.team5.techradar.security.jwt.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtService {
    private final JwtUtil jwtUtil;

    public String generateAccessToken(String username, Role role) {
        return jwtUtil.generateAccessToken(username, role);
    }

    public String getUsernameFromToken(String token) {
        return jwtUtil.getUserNameFromToken(token);
    }

    public String getRoleNameFromToken(String token) {
        return jwtUtil.getRoleFromToken(token);
    }


    public boolean validate(String token) {
        return StringUtils.hasText(token) && jwtUtil.validateAccessToken(token);
    }
}
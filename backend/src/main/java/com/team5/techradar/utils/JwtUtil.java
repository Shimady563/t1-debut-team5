package com.team5.techradar.utils;

import com.team5.techradar.model.Role;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JwtUtil {

    @Value("${jwt.tokenLifeTime}")
    private String tokenLifeTimeMs;

    @Value("${jwt.secret}")
    private String secret;

    public String generateAccessToken(String username, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        Instant start = Instant.now();
        Duration duration = Duration.ofMillis(Long.parseLong(tokenLifeTimeMs));
        Instant end = start.plus(duration);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(Date.from(start))
                .setExpiration(Date.from(end))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return (String) Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    public long getTokenIssuedAt(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getIssuedAt()
                .getTime();
    }

    public long getTokenExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .getTime();
    }
}
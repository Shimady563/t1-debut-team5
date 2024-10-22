package com.team5.techradar.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.techradar.model.Role;
import com.team5.techradar.security.jwt.service.JwtService;
import com.team5.techradar.security.response.InvalidTokenResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private static final String TOKEN_HEADER = "Authorization";

    @Value("${auth.whitelist}")
    private final String[] whitelist;

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getJWTFromRequest(request);
        boolean validationResult = jwtService.validate(token);
        log.info("JWT Token validation result: {}", validationResult);
        if (validationResult) {
            String username = jwtService.getUsernameFromToken(token);
            String role = jwtService.getRoleNameFromToken(token);
            log.info("User validated, email : {}", username);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, List.of(Role.valueOf(role)));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String body = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new InvalidTokenResponse("Invalid token"));
            response.getWriter().write(body);
        }
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        for (String path : whitelist) {
            if (new AntPathRequestMatcher(path).matches(request)) {
                return true;
            }
        }
        return false;
    }


    private String getJWTFromRequest(HttpServletRequest request) {
        String header = request.getHeader(TOKEN_HEADER);
        if (header != null) {
            return header.substring(7);
        }
        return null;
    }
}
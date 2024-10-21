package com.team5.techradar.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.techradar.model.Role;
import com.team5.techradar.security.response.InvalidTokenResponse;
import com.team5.techradar.security.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private static final String TOKEN_HEADER = "Authorization";

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    private final RequestMatcher uriMatcher =
            new AntPathRequestMatcher("/auth/**", HttpMethod.POST.name());


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = getJWTFromRequest(request);
        boolean validationResult = jwtService.validate(token);
        log.info("JWT Token validation result: {}", validationResult);
        if (validationResult) {
            String username = jwtService.getUsernameFromToken(token);
            String role = jwtService.getRoleNameFromToken(token);
            log.info("Username : {}", username);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, List.of(Role.valueOf(role)));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String body = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new InvalidTokenResponse("Invalid token"));
            response.getWriter().write(body);
        }
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return uriMatcher.matches(request);
    }


    private String getJWTFromRequest(HttpServletRequest request) {
        String header = request.getHeader(TOKEN_HEADER);
        if (header != null) {
            return header.substring(7);
        }
        return null;
    }
}
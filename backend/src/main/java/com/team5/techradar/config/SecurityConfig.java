package com.team5.techradar.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.techradar.handler.CustomAuthenticationFailureHandler;
import com.team5.techradar.handler.CustomAuthenticationSuccessHandler;
import com.team5.techradar.handler.CustomLogoutSuccessHandler;
import com.team5.techradar.repository.UserRepository;
import com.team5.techradar.security.filter.JwtFilter;
import com.team5.techradar.security.jwt.service.JwtService;
import com.team5.techradar.security.jwt.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final String[] AUTH_WHITELIST_PATHS = new String[]{
            "/technologies/**",
            "/users/signup",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/auth/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (email) -> userRepository.findFetchSpecializationByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtService jwtService, ObjectMapper objectMapper) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    auth.requestMatchers(AUTH_WHITELIST_PATHS).permitAll();
                    auth.requestMatchers("/users/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/specializations/**").hasRole("ADMIN");
                    //auth.requestMatchers("").hasRole("USER");
                    auth.anyRequest().authenticated();
                })
                .addFilterAt(getJWTAuthFilter(jwtService, objectMapper), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JwtFilter getJWTAuthFilter(JwtService jwtService, ObjectMapper objectMapper) {
        return new JwtFilter(jwtService, objectMapper);
    }

    @Bean
    public JwtUtil getJwtUtil() {
        return new JwtUtil();
    }
}

package com.team5.techradar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.techradar.filter.JwtFilter;
import com.team5.techradar.service.JwtService;
import com.team5.techradar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@TestConfiguration
public class TestConfig {

    @Value("${auth.whitelist}")
    private String[] whitelist;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    auth.requestMatchers(whitelist).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterAt(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // prevent filter registration in servlet
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistrationBean(JwtFilter jwtFilter) {
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>(jwtFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(whitelist, jwtService(), new ObjectMapper());
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService(jwtUtil());
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}

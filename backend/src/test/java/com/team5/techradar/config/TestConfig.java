package com.team5.techradar.config;

import com.team5.techradar.handler.CustomAuthenticationFailureHandler;
import com.team5.techradar.handler.CustomAuthenticationSuccessHandler;
import com.team5.techradar.handler.CustomLogoutSuccessHandler;
import com.team5.techradar.map.UserRegistrationRequestMap;
import com.team5.techradar.map.UserResponseMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@TestConfiguration
public class TestConfig {

    private final String[] AUTH_WHITELIST_PATHS = new String[]{
            "/users/signup",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(false);
        mapper.addMappings(new UserResponseMap());
        mapper.addMappings(new UserRegistrationRequestMap());
        return mapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // failure handler to return error json response
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    // success handler to return auth success status
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    // success handler to return logout success status
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(AUTH_WHITELIST_PATHS).permitAll();
                    auth.requestMatchers("/users/**").hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers("/specializations/**").hasRole("ADMIN");
                    //auth.requestMatchers("").hasRole("USER");
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/users/login").permitAll();
                    form.failureHandler(authenticationFailureHandler());
                    form.successHandler(authenticationSuccessHandler());
                })
                .logout(logout -> {
                    logout.logoutUrl("/users/logout").permitAll();
                    logout.logoutSuccessHandler(logoutSuccessHandler());
                })
                .build();
    }
}

package com.team5.techradar.config;


import com.team5.techradar.handler.CustomAuthenticationFailureHandler;
import com.team5.techradar.handler.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return (email) -> userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
//    }

    // failure handler to return error json response
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    // success handler to return logout success status
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("").hasRole("USER");
                    auth.requestMatchers("").hasRole("ADMIN");
                    auth.requestMatchers("").permitAll();
                })
                .formLogin(form -> {
                    form.loginPage("/api/v1/users/login");
                    form.failureHandler(authenticationFailureHandler());
                })
                .logout(logout -> {
                    logout.logoutUrl("/api/v1/users/logout");
                    logout.logoutSuccessHandler(logoutSuccessHandler());
                })
                .build();
    }
}

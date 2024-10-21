package com.team5.techradar.security.verifier;

import com.team5.techradar.model.User;
import com.team5.techradar.model.dto.UserLoginRequest;
import com.team5.techradar.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserVerifier {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public boolean verify(UserLoginRequest user) {
        try{
            log.info("Verifying user {}", user);
            User userEntity = userService.findUserByEmail(user.getEmail());
            log.info("Found user {}", userEntity);
            return userEntity.getUsername().equals(user.getEmail())
                    && passwordEncoder.matches(user.getPassword(), userEntity.getPassword());
        }
        catch (UsernameNotFoundException exception){
            return false;
        }
    }
}

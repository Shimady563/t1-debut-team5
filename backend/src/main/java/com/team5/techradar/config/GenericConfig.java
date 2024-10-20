package com.team5.techradar.config;

import com.team5.techradar.map.TechnologyResponseMap;
import com.team5.techradar.map.UserRegistrationRequestMap;
import com.team5.techradar.map.UserResponseMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(false);
        mapper.addMappings(new UserResponseMap());
        mapper.addMappings(new UserRegistrationRequestMap());
        mapper.addMappings(new TechnologyResponseMap());
        return mapper;
    }
}

package com.team5.techradar.config;

import com.team5.techradar.map.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
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
        mapper.addMappings(new UserTechnologyResponseMap());
        mapper.addMappings(new TechnologyStatsResponseMap());
        mapper.addMappings(new VoteCreationRequestMap());
        mapper.addMappings(new VoteResponseMap());
        mapper.addMappings(new UserVoteResponseMap());
        mapper.addMappings(new SpecializationResponseMap());
        return mapper;
    }
}

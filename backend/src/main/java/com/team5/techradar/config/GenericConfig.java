package com.team5.techradar.config;

import com.team5.techradar.map.*;
import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@EnableCaching
@Configuration
public class GenericConfig {

    @Bean
    public NewTopic technologyTopic() {
        return TopicBuilder.name("technologyTopic")
                .partitions(4)
                .replicas(1)
                .build();
    }

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
        return mapper;
    }
}

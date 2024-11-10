package com.team5.techradar.config;

import com.team5.techradar.map.*;
import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

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

    // Setting max attempts to 2 to avoid retries and infinite loop
    @Bean
    public DefaultErrorHandler errorHandler() {
        return new DefaultErrorHandler(
                (record, exception) -> System.out.println("Discarding message due to: " + exception.getCause().getMessage()),
                new FixedBackOff(0L, 1)
        );
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

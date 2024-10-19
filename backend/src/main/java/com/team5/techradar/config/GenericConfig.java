package com.team5.techradar.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class GenericConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

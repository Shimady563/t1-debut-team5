package com.team5.techradar.service;

import com.team5.techradar.model.dto.TechnologyPayload;
import com.team5.techradar.model.dto.TechnologyPayloadResponse;
import com.team5.techradar.model.dto.TechnologyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class IntegrationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ModelMapper mapper;

    public void mapTechnology(String key, TechnologyPayload message) {
        log.info("Mapping received technology with name: {}", message.getName());
        TechnologyPayloadResponse mappedMessage = mapper.map(message, TechnologyPayloadResponse.class);
        kafkaTemplate.send("technologyTopic", key, mappedMessage);
    }
}

package com.team5.techradar.service;

import com.team5.techradar.model.dto.TechnologyPayload;
import com.team5.techradar.model.dto.TechnologyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class IntegrationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void mapTechnologies(String key, List<TechnologyPayload> message) {
        // mapping technologies to be able to visualize them on radar
        List<TechnologyResponse> mappedMessage = new ArrayList<>();
        kafkaTemplate.send("technologyTopic", key, mappedMessage);
    }
}

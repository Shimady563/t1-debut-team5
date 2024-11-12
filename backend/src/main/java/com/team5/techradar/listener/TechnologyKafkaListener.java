package com.team5.techradar.listener;

import com.team5.techradar.model.dto.TechnologyPayload;
import com.team5.techradar.service.IntegrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TechnologyKafkaListener {

    private final IntegrationService integrationService;

    @KafkaListener(topics = "externalTechnologyTopic", groupId = "technology")
    public void listenTechnologyMessage(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Payload @Valid TechnologyPayload message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition
    ) {
        log.info("Received technology message with name: {}, from partition: {}", message.getName(), partition);
        integrationService.mapTechnology(key, message);
    }
}

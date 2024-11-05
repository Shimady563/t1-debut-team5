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

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TechnologyKafkaListener {

    private final IntegrationService integrationService;

    @KafkaListener(topics = "externalTechnologyTopic", groupId = "technology")
    public void listenTechnologiesMessage(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Payload @Valid List<TechnologyPayload> message,
            @Header(KafkaHeaders.PARTITION) Integer partition
    ) {
        log.info("Received technologies message from partition {}", partition);
        integrationService.mapTechnologies(key, message);
    }
}

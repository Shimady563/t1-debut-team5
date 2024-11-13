package com.team5.techradar.listener;

import com.redis.testcontainers.RedisContainer;
import com.team5.techradar.config.KafkaTestConfig;
import com.team5.techradar.model.dto.TechnologyPayload;
import com.team5.techradar.service.IntegrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.after;


@SpringBootTest
@Testcontainers
@Import(KafkaTestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TechnologyKafkaListenerIntegrationTest {
    @Container
    @ServiceConnection
    private static final RedisContainer REDIS = new RedisContainer("redis:alpine");
    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("techradar")
            .withUsername("postgres")
            .withPassword("postgres");
    @Container
    @ServiceConnection
    private static final KafkaContainer KAFKA = new KafkaContainer();

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @SpyBean
    private IntegrationService integrationService;

    @SpyBean
    private TechnologyKafkaListener listener;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @BeforeEach
    public void setUp() {
        // Wait for the listener container to be assigned a partition
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(container ->
                ContainerTestUtils.waitForAssignment(container, 4));
    }

    @Test
    public void testListenTechnologyMessage() {
        var key = "1";
        var partition = 0;
        var message = new TechnologyPayload();
        message.setName("Java");
        message.setId(1L);
        message.setCategory("Languages");
        message.setUsageLevel(0.6);

        kafkaTemplate.send("skillTopic", partition, key, message);

        then(listener).should(after(5000)).listenTechnologyMessage(key, message, partition);
        then(integrationService).should().mapTechnology(key, message);
    }
}

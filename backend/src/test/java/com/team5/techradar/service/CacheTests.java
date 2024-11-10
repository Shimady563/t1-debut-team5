package com.team5.techradar.service;

import com.redis.testcontainers.RedisContainer;
import com.team5.techradar.repository.TechnologyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cache.CacheManager;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CacheTests {
    @Container
    @ServiceConnection
    private static final RedisContainer REDIS = new RedisContainer("redis:alpine");
    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("techradar")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    private TechnologyService technologyService;

    @SpyBean
    private TechnologyRepository technologyRepository;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testTechnologyStatsCaching() {
        technologyService.getAllTechnologiesWithUsageStats();
        technologyService.getAllTechnologiesWithUsageStats();

        then(technologyRepository).should(times(1)).findAllFetchUsers();
        assertThat(cacheManager.getCacheNames()).isNotEmpty();
        assertNotNull(cacheManager.getCache("technologyStats"));
    }
}

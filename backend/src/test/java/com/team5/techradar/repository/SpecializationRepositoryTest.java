package com.team5.techradar.repository;

import com.team5.techradar.model.Specialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpecializationRepositoryTest {
    @Autowired
    protected TestEntityManager entityManager;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("techradar")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    private SpecializationRepository specializationRepository;

    private final Specialization specialization1 = new Specialization();

    @BeforeEach
    public void setUp() {
        specialization1.setName("Backend");
        entityManager.persist(specialization1);
        entityManager.flush();
    }

    @Test
    public void testFindAll() {
        var specializations = specializationRepository.findAll();

        assertThat(specializations).hasSize(10);
    }

    @Test
    public void testFindById() {
        var optionalSpecialization = specializationRepository.findById(specialization1.getId());

        assertTrue(optionalSpecialization.isPresent());
        assertEquals(specialization1.getId(), optionalSpecialization.get().getId());
    }


    @Test
    public void testSave() {
        Specialization specialization = new Specialization();
        specializationRepository.save(specialization);

        Optional<Specialization> savedSpecialization = specializationRepository.findById(specialization.getId());

        assertTrue(savedSpecialization.isPresent());
        assertEquals(specialization.getId(), savedSpecialization.get().getId());
    }
}

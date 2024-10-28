package com.team5.techradar.repository;

import com.team5.techradar.model.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TechnologyRepositoryTest {

    @Autowired
    protected TestEntityManager entityManager;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("techradar")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    private TechnologyRepository technologyRepository;

    private final Technology technology1 = new Technology();
    private final Technology technology2 = new Technology();
    private final Specialization specialization = new Specialization();
    private final User user = new User();

    @BeforeEach
    public void setUp() {
        specialization.setName("Backend");
        entityManager.persist(specialization);

        technology1.setName("Java");
        technology1.setLevel(Level.ADOPT);
        technology1.setType(Type.LANGUAGES);
        technology1.setMoved(Moved.NOT_MOVED);
        technology1.setIsActive(true);

        technology2.setName("Postgres");
        technology2.setLevel(Level.ASSESS);
        technology2.setType(Type.DATABASES);
        technology2.setMoved(Moved.UP);
        technology2.setIsActive(false);

        user.setEmail("email@mail.com");
        user.setPassword("password");
        user.setSpecialization(specialization);
        user.addTechnology(technology1);
        user.addTechnology(technology2);

        technology1.addUser(user);
        technology2.addUser(user);
        entityManager.persist(technology1);
        entityManager.persist(technology2);
        entityManager.persist(user);

        entityManager.flush();
    }

    @Test
    public void testFindById() {
        var foundTechnology = technologyRepository.findById(technology1.getId());

        assertTrue(foundTechnology.isPresent());
        assertEquals(technology1.getId(), foundTechnology.get().getId());
    }

    @Test
    public void testSave() {
        var technology = new Technology();
        technology.setName("name");
        technology.setLevel(Level.HOLD);
        technology.setType(Type.PLATFORMS);
        technology.setMoved(Moved.DOWN);
        technology.setIsActive(true);

        technologyRepository.save(technology);

        var technologyOptional = technologyRepository.findById(technology.getId());
        assertTrue(technologyOptional.isPresent());
    }

    @Test
    public void testDelete() {
        technologyRepository.delete(technology1);
        var technologyOptional = technologyRepository.findById(technology1.getId());
        assertTrue(technologyOptional.isEmpty());
    }

    @Test
    public void testFindAll() {
        var technologies = technologyRepository.findAll();
        technologies.stream().map(Technology::getName).forEach(System.out::println);

        assertThat(technologies).hasSize(12);
    }

    @Test
    public void testFindAllByIsActive() {
        var isActive = true;
        var technologies = technologyRepository.findAllByIsActive(isActive);

        assertThat(technologies).hasSize(10);
    }

    @Test
    public void testFindFetchUsersById() {
        var foundTechnology = technologyRepository.findById(technology1.getId());

        assertTrue(foundTechnology.isPresent());
        assertEquals(technology1.getId(), foundTechnology.get().getId());
        assertNotNull(foundTechnology.get().getUsers());
        assertThat(foundTechnology.get().getUsers()).hasSize(1);
    }

    @Test
    public void testFindAllFetchUsers() {
        var foundTechnologies = technologyRepository.findAllFetchUsers();

        assertThat(foundTechnologies).hasSize(12)
                .extracting(Technology::getUsers)
                .contains(technology1.getUsers(), technology2.getUsers());
    }
}

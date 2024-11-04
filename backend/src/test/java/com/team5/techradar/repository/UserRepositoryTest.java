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
public class UserRepositoryTest {
    @Autowired
    protected TestEntityManager entityManager;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("techradar")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    private UserRepository userRepository;

    private final User user = new User();
    private final Technology technology1 = new Technology();
    private final Technology technology2 = new Technology();
    private final Specialization specialization = new Specialization();

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
        technology2.setIsActive(true);

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
        var foundUser = userRepository.findById(user.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getId(), foundUser.get().getId());
    }

    @Test
    public void testSave() {
        var user1 = new User();
        user1.setEmail("mail@mail.com");
        user1.setPassword("password");

        userRepository.save(user1);

        var userOptional = userRepository.findById(user1.getId());
        assertTrue(userOptional.isPresent());
        assertEquals(user1.getEmail(), userOptional.get().getEmail());
    }

    @Test
    public void testFindFetchSpecializationByEmail() {
        var foundUser = userRepository.findById(user.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertNotNull(foundUser.get().getSpecialization());
        assertEquals(user.getSpecialization().getName(), foundUser.get().getSpecialization().getName());
    }

    @Test
    public void testFindFetchTechnologiesByEmail() {
        var foundUser = userRepository.findById(user.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertNotNull(foundUser.get().getTechnologies());
        assertThat(foundUser.get().getTechnologies())
                .hasSize(2)
                .extracting(Technology::getName)
                .contains(technology1.getName(), technology2.getName());
    }

    @Test
    public void testFindByEmail() {
        var foundUser = userRepository.findByEmail(user.getEmail());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    public void testExistsByEmail() {
        var exists = userRepository.existsByEmail(user.getEmail());

        assertTrue(exists);
    }
}

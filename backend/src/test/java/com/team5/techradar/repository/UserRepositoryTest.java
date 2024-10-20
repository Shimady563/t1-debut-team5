package com.team5.techradar.repository;

import com.team5.techradar.model.Specialization;
import com.team5.techradar.model.User;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    protected TestEntityManager entityManager;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("techradar")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    private UserRepository userRepository;

    private final User user = new User();

    @BeforeEach
    public void setUp() {
        var specialization = new Specialization();
        specialization.setName("Backend");
        entityManager.persist(specialization);

        user.setEmail("email@mail.com");
        user.setPassword("password");
        user.setSpecialization(specialization);
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
    }
}

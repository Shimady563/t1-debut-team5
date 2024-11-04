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
import static org.assertj.core.api.BDDAssertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VoteRepositoryTest {

    @Autowired
    protected TestEntityManager entityManager;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("techradar")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    private VoteRepository voteRepository;

    private final User user = new User();
    private final Technology technology1 = new Technology();
    private final Technology technology2 = new Technology();
    private final Vote vote1 = new Vote();
    private final Vote vote2 = new Vote();

    @BeforeEach
    public void setUp() {
        Specialization specialization = new Specialization();
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

        vote1.setLevel(Level.HOLD);
        vote1.setTechnology(technology1);
        vote1.setUser(user);
        entityManager.persist(vote1);

        vote2.setLevel(Level.TRIAL);
        vote2.setTechnology(technology2);
        vote2.setUser(user);
        entityManager.persist(vote2);

        entityManager.flush();
    }

    @Test
    public void testSave() {
        var vote = new Vote();
        vote.setLevel(Level.TRIAL);

        voteRepository.save(vote);

        var voteOptional = voteRepository.findById(vote.getId());

        assertTrue(voteOptional.isPresent());
    }

    @Test
    public void testFindById() {
        var voteOptional = voteRepository.findById(vote1.getId());

        assertTrue(voteOptional.isPresent());
        assertEquals(vote1.getId(), voteOptional.get().getId());
    }

    @Test
    public void testFindAll() {
        var foundVotes = voteRepository.findAll();

        assertThat(foundVotes).hasSize(11)
                .extracting(Vote::getUser, Vote::getTechnology)
                .contains(
                        tuple(user, technology1),
                        tuple(user, technology2)
                );
    }

    @Test
    public void testFindFetchAllByTechnology() {
        var foundVotes = voteRepository.findFetchAllByTechnology(technology1);

        assertThat(foundVotes).hasSize(1)
                .extracting(Vote::getUser, Vote::getTechnology)
                .contains(tuple(user, technology1));
    }

    @Test
    public void testFindFetchTechnologyAllByUser() {
        var foundVotes = voteRepository.findFetchTechnologyAllByUser(user);

        assertThat(foundVotes).hasSize(2)
                .extracting(Vote::getTechnology)
                .contains(technology1, technology2);
    }

    @Test
    public void testDeleteByTechnology() {
        voteRepository.deleteByTechnology(technology1);

        var foundVotes = voteRepository.findFetchAllByTechnology(technology1);

        assertThat(foundVotes).hasSize(0);
    }
}

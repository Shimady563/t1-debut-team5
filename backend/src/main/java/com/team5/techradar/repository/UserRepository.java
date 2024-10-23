package com.team5.techradar.repository;

import com.team5.techradar.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = "specialization")
    Optional<User> findFetchSpecializationByEmail(String email);

    boolean existsByEmail(String email);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = "technologies")
    Optional<User> findFetchTechnologiesByEmail(String email);
}

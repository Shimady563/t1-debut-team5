package com.team5.techradar.repository;

import com.team5.techradar.model.Technology;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    List<Technology> findAllByIsActive(Boolean isActive);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "users")
    Optional<Technology> findFetchUsersById(Long id);
}

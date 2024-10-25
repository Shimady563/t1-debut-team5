package com.team5.techradar.repository;

import com.team5.techradar.model.Technology;
import com.team5.techradar.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"user", "technology"})
    List<Vote> findFetchAllByTechnology(Technology technology);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"user", "technology"})
    List<Vote> findAll();
}

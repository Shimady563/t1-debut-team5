package com.team5.techradar.repository;

import com.team5.techradar.model.Technology;
import com.team5.techradar.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @EntityGraph(value = "vote_user_technology_load_graph")
    List<Vote> findFetchAllByTechnology(Technology technology);

    @EntityGraph(value = "vote_user_technology_load_graph")
    List<Vote> findAll();

    void deleteByTechnology(Technology technology);
}

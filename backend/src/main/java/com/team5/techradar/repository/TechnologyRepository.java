package com.team5.techradar.repository;

import com.team5.techradar.model.Technology;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    List<Technology> findAllByIsActive(Boolean isActive);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "users")
    Optional<Technology> findFetchUsersById(Long id);

    @Query("select t from Technology t left join fetch t.users")
    List<Technology> findAllFetchUsers();

    @Query("select t from Technology t left join fetch t.votes")
    List<Technology> findAllFetchVotes();
}

package com.team5.techradar.repository;

import com.team5.techradar.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    List<Technology> findAllByIsActive(Boolean isActive);
}

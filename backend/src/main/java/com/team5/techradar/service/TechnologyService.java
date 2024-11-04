package com.team5.techradar.service;

import com.team5.techradar.exception.ResourceNotFoundException;
import com.team5.techradar.model.Moved;
import com.team5.techradar.model.Technology;
import com.team5.techradar.model.dto.TechnologyCreationRequest;
import com.team5.techradar.model.dto.TechnologyResponse;
import com.team5.techradar.model.dto.TechnologyStatsResponse;
import com.team5.techradar.model.dto.TechnologyUpdateRequest;
import com.team5.techradar.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final ModelMapper mapper;

    protected Technology getTechnologyById(Long id) {
        log.info("Getting technology by id {}", id);
        return technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Technology with id: " + id + " not found"));
    }

    protected Technology getTechnologyByIdFetchUsers(Long id) {
        log.info("Getting technology with users by id {}", id);
        return technologyRepository.findFetchUsersById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Technology with id: " + id + " not found"));
    }

    protected List<Technology> getAllTechnologiesByIds(List<Long> ids) {
        log.info("Getting all technologies by ids {}", ids);
        return technologyRepository.findAllById(ids);
    }

    @Transactional(readOnly = true)
    public List<TechnologyResponse> getAllTechnologies() {
        log.info("Getting all technologies");
        return technologyRepository.findAll().stream()
                .map(o -> mapper.map(o, TechnologyResponse.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TechnologyResponse> getAllTechnologiesByActivity(Boolean isActive) {
        log.info("Getting all technologies by activity {}", isActive);
        return technologyRepository.findAllByIsActive(isActive).stream()
                .map(o -> mapper.map(o, TechnologyResponse.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TechnologyStatsResponse> getAllTechnologiesWithUsageStats() {
        return technologyRepository.findAllFetchUsers().stream()
                .map(t -> mapper.map(t, TechnologyStatsResponse.class))
                .toList();
    }

    @Transactional
    public void createTechnology(TechnologyCreationRequest request) {
        log.info("Creating technology with name {}", request.getName());
        Technology technology = mapper.map(request, Technology.class);
        technologyRepository.save(technology);
    }

    @Transactional
    public void updateTechnology(Long id, TechnologyUpdateRequest request) {
        log.info("Updating technology with id: {}", id);
        Technology technology = getTechnologyById(id);
        technology.setName(request.getName());
        technology.setLevel(request.getLevel());
        technology.setType(request.getType());
        technology.setMoved(mapMoved(request.getMoved()));
        technology.setIsActive(request.getIsActive());
        technologyRepository.save(technology);
    }

    @Transactional
    public void deleteTechnology(Long id) {
        log.info("Deleting technology with id: {}", id);
        Technology technology = getTechnologyByIdFetchUsers(id);
        technology.getUsers().forEach(user -> user.removeTechnology(technology));
        technologyRepository.delete(technology);
    }

    private Moved mapMoved(Integer moved) {
        if (moved == -1) {
            return Moved.DOWN;
        } else if (moved == 0) {
            return Moved.NOT_MOVED;
        }
        return Moved.UP;
    }
}

package com.energizeglobal.itpm.service.impl;

import com.energizeglobal.itpm.model.ProjectEntity;
import com.energizeglobal.itpm.model.SprintEntity;
import com.energizeglobal.itpm.model.dto.SprintDto;
import com.energizeglobal.itpm.repository.SprintRepository;
import com.energizeglobal.itpm.service.Mapper;
import com.energizeglobal.itpm.service.ProjectService;
import com.energizeglobal.itpm.service.SprintService;
import com.energizeglobal.itpm.util.exceptions.InvalidActionException;
import com.energizeglobal.itpm.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {
    private static final Logger log = Logger.getLogger(SprintServiceImpl.class);
    private final SprintRepository sprintRepository;
    private final Mapper mapper;
    private final ProjectService projectService;

    @Override
    @Transactional
    public void addSprintToProject(SprintDto sprintDto) {
        sprintDto.setId(null);
        log.trace("adding sprint to Project: " + sprintDto);
        final SprintEntity sprintEntity = new SprintEntity();
        mapper.map(sprintDto, sprintEntity);
        sprintEntity.setIsFinished(false);
        sprintEntity.setIsRunning(false);
        sprintRepository.save(sprintEntity);
        log.trace("Sprint attached to Project: " + sprintEntity);
    }

    @Override
    @Transactional
    public void changeDeadLine(SprintDto sprintDto) {
        final SprintEntity sprintEntity = findEntityById(sprintDto.getId());
        log.trace("Deadline of sprint: " + sprintEntity + " changing to: " + sprintEntity.getDeadLine());
        sprintEntity.setDeadLine(sprintDto.getDeadLine());

        sprintRepository.save(sprintEntity);
        log.trace("Deadline of sprint: " + sprintEntity + " changed to " + sprintDto.getDeadLine());
        sprintRepository.flush();
    }

    @Override
    public Page<SprintDto> findAllSprintsByProjectId(String projectId, Pageable pageable) {
        log.trace("Searching all sprints by projectId: " + projectId);
        final Page<SprintEntity> allByProjectEntity = sprintRepository
                .findAllByProjectEntity(projectService.findEntityById(projectId), pageable);
        log.trace("Sprints found: " + allByProjectEntity.getTotalElements());
        return allByProjectEntity
                .map(sprintEntity -> mapper.map(sprintEntity, new SprintDto()));
    }


    @Override
    public SprintEntity findEntityById(Long sprintId) {
        log.trace("Searching Sprint by id: " + sprintId);
        final Optional<SprintEntity> byId = sprintRepository
                .findById(sprintId);

        String resultLog = byId
                .map(e -> "sprint with id" + sprintId + " found.")
                .orElseGet(() -> "Sprint with id " + sprintId + " not found.");
        log.trace(resultLog);

        return byId
                .orElseThrow(() -> new NotFoundException("Sprint with id: " + sprintId + " not found."));
    }

    @Override
    public SprintDto findActiveSprintByProjectId(String projectId) {
        final ProjectEntity projectEntity = projectService.findEntityById(projectId);
        final Optional<SprintEntity> sprintEntityOptional = sprintRepository.findByProjectEntityAndIsRunningTrue(projectEntity);
        final SprintEntity sprintEntity = sprintEntityOptional.orElseThrow(() -> new NotFoundException("Sprint not found"));
        return mapper.map(sprintEntity, new SprintDto());
    }

    @Override
    @Transactional
    public void startSprint(SprintDto sprintDto) {
        final SprintEntity sprintEntity = findEntityById(sprintDto.getId());
        final ProjectEntity projectEntity = sprintEntity.getProjectEntity();
        for (SprintEntity sprint : projectEntity.getSprintEntities()) {
            final boolean isSprintRunning = sprint.getIsRunning();
            if (isSprintRunning) {
                throw new InvalidActionException("A one sprint has already been launched in the project.");
            }
        }

        sprintEntity.setStartDate(LocalDate.now());
        sprintEntity.setDeadLine(sprintDto.getDeadLine());
        sprintEntity.setIsRunning(true);
        sprintRepository.save(sprintEntity);
    }

    @Override
    public SprintDto findById(Long sprintId) {
        final SprintEntity sprintEntity = findEntityById(sprintId);
        return mapper.map(sprintEntity, new SprintDto());
    }

    @Override
    public List<SprintDto> findAllSprintWhichNotFinished(String projectId) {
        final ProjectEntity projectEntity = projectService.findEntityById(projectId);
        log.trace("Searching all");
        return sprintRepository.findAllByProjectEntityAndIsFinishedFalse(projectEntity)
                .stream().map(sprintEntity -> mapper.map(sprintEntity, new SprintDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SprintDto> search(String searchText) {
        return sprintRepository.findAllByNameContains(searchText)
                .stream().map(sprintEntity -> mapper.map(sprintEntity, new SprintDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SprintDto> searchInProject(String searchText, String projectId) {
        final ProjectEntity projectEntity = projectService.findEntityById(projectId);

        return sprintRepository.findAllByNameContainsAndProjectEntity(searchText, projectEntity)
                .stream().map(sprintEntity -> mapper.map(sprintEntity, new SprintDto()))
                .collect(Collectors.toList());
    }
}



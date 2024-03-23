package com.energizeglobal.itpm.service;

import com.energizeglobal.itpm.model.SprintEntity;
import com.energizeglobal.itpm.model.dto.SprintDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface SprintService {
    void addSprintToProject(SprintDto sprintDto);

    Page<SprintDto> findAllSprintsByProjectId(String projectId, Pageable pageable);

    void changeDeadLine(SprintDto sprintDto);

    SprintEntity findEntityById(Long sprintId);

    SprintDto findActiveSprintByProjectId(String projectId);

    @Transactional
    void startSprint(SprintDto sprintDto);

    SprintDto findById(Long sprintId);

    List<SprintDto> findAllSprintWhichNotFinished(String projectId);

    List<SprintDto> search(String searchText);

    List<SprintDto> searchInProject(String searchText, String projectId);
}

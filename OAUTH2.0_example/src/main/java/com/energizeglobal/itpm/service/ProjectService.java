package com.energizeglobal.itpm.service;

import com.energizeglobal.itpm.model.ProjectEntity;
import com.energizeglobal.itpm.model.dto.ProjectDto;
import com.energizeglobal.itpm.model.dto.UserProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    void createProject(ProjectDto projectDto);

    void updateProject(ProjectDto projectDto);

    void removeProject(String projectId);

    ProjectDto findById(String projectId);

    ProjectEntity findEntityById(String projectId);

    Page<ProjectDto> findAllByAssignedUserId(String userId, Pageable pageable);

    void attachUserToProject(UserProjectDto userProjectDto);

    List<ProjectDto> search(String text);


}

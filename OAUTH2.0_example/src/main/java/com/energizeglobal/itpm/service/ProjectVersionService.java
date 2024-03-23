package com.energizeglobal.itpm.service;

import com.energizeglobal.itpm.model.ProjectVersionEntity;
import com.energizeglobal.itpm.model.dto.ProjectVersionDto;
import com.energizeglobal.itpm.model.enums.ProjectVersionStatus;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProjectVersionService {

    ProjectVersionEntity findEntityById(Long versionId);

    void createNewVersion(ProjectVersionDto versionDto);

    ProjectVersionDto findById(Long versionId);

    List<ProjectVersionDto> findAllByProjectId(String projectId);

    List<ProjectVersionDto> findAllByProjectIdAndStatus(String projectId, ProjectVersionStatus status, Sort sort);

    void remove(Long versionId);

    void update(ProjectVersionDto projectVersionDto);
}

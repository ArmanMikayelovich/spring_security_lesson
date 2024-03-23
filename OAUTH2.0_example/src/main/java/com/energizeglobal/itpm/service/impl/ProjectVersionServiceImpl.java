package com.energizeglobal.itpm.service.impl;

import com.energizeglobal.itpm.model.ProjectEntity;
import com.energizeglobal.itpm.model.ProjectVersionEntity;
import com.energizeglobal.itpm.model.dto.ProjectVersionDto;
import com.energizeglobal.itpm.model.enums.ProjectVersionStatus;
import com.energizeglobal.itpm.repository.ProjectVersionRepository;
import com.energizeglobal.itpm.service.Mapper;
import com.energizeglobal.itpm.service.ProjectService;
import com.energizeglobal.itpm.service.ProjectVersionService;
import com.energizeglobal.itpm.util.exceptions.AlreadyExistsException;
import com.energizeglobal.itpm.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectVersionServiceImpl implements ProjectVersionService {
    private final ProjectService projectService;
    private final ProjectVersionRepository versionRepository;
    private final Mapper mapper;

    @Override
    public ProjectVersionEntity findEntityById(Long versionId) {
        return versionRepository.findById(versionId).orElseThrow(() ->
                new NotFoundException("Project version with id: " + versionId + " not found")
        );
    }

    @Override
    @Transactional
    public void createNewVersion(ProjectVersionDto versionDto) {
        final ProjectEntity projectEntity = projectService.findEntityById(versionDto.getProjectId());
        versionRepository.findByProjectEntityAndVersion(projectEntity, versionDto.getVersion()).ifPresent(ignored -> {
            throw new AlreadyExistsException("Project version with name " + versionDto.getVersion() + " already exists");
        });

        final ProjectVersionEntity map = mapper.map(versionDto, new ProjectVersionEntity());
        versionRepository.save(map);
    }

    @Override
    public ProjectVersionDto findById(Long versionId) {
        final ProjectVersionEntity versionEntity = findEntityById(versionId);
        return mapper.map(versionEntity, new ProjectVersionDto());
    }

    @Override
    public List<ProjectVersionDto> findAllByProjectIdAndStatus(String projectId, ProjectVersionStatus status, Sort sort) {
        final ProjectEntity projectEntity = projectService.findEntityById(projectId);

        final List<ProjectVersionEntity> versionEntityList
                = versionRepository.findAllByProjectEntityAndVersionStatus(projectEntity, status, sort);

        return versionEntityList
                .stream()
                .map(versionEntity -> mapper.map(versionEntity, new ProjectVersionDto()))
                .collect(Collectors.toList());

    }

    @Override
    public List<ProjectVersionDto> findAllByProjectId(String projectId) {
        final ProjectEntity projectEntity = projectService.findEntityById(projectId);
        final List<ProjectVersionEntity> allByProjectEntity = versionRepository.findAllByProjectEntity(projectEntity);

        return allByProjectEntity
                .stream().
                        map(versionEntity -> mapper.map(versionEntity, new ProjectVersionDto()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long versionId) {
        final ProjectVersionEntity versionEntity = findEntityById(versionId);
        versionRepository.delete(versionEntity);
    }

    @Override
    @Transactional
    public void update(ProjectVersionDto projectVersionDto) {
        final ProjectVersionEntity versionEntity = findEntityById(projectVersionDto.getId());
        mapper.map(projectVersionDto, versionEntity);
        versionRepository.save(versionEntity);
    }


}

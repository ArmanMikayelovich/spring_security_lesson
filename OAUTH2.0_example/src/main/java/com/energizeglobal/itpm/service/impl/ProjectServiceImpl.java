package com.energizeglobal.itpm.service.impl;

import com.energizeglobal.itpm.model.ProjectEntity;
import com.energizeglobal.itpm.model.UserEntity;
import com.energizeglobal.itpm.model.UserProjectEntity;
import com.energizeglobal.itpm.model.dto.ProjectDto;
import com.energizeglobal.itpm.model.dto.UserProjectDto;
import com.energizeglobal.itpm.model.enums.UserRole;
import com.energizeglobal.itpm.repository.ProjectRepository;
import com.energizeglobal.itpm.repository.UserProjectRepository;
import com.energizeglobal.itpm.service.Mapper;
import com.energizeglobal.itpm.service.ProjectService;
import com.energizeglobal.itpm.service.UserService;
import com.energizeglobal.itpm.util.exceptions.AlreadyExistsException;
import com.energizeglobal.itpm.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {
    private static final Logger log = Logger.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;
    private final Mapper mapper;
    private final UserProjectRepository userProjectRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void createProject(ProjectDto projectDto) {
        log.trace("creating Project: " + projectDto);
        projectDto.setCreatedAt(null);


        projectRepository.findById(projectDto.getId()).ifPresent(projectEntity -> {
            throw new AlreadyExistsException("Project with key: " + projectDto.getId() + " already exists.");
        });
        final ProjectEntity projectEntity = mapper.map(projectDto, new ProjectEntity());
        projectRepository.save(projectEntity);
        final UserEntity creator = userService.findEntityById(projectDto.getCreatorId());
        final UserProjectEntity userProjectEntity = new UserProjectEntity();
        userProjectEntity.setUserEntity(creator);
        userProjectEntity.setProjectEntity(projectEntity);
        userProjectEntity.setRole(UserRole.ADMINISTRATORS);
        userProjectRepository.save(userProjectEntity);
        log.trace("Project created: " + projectEntity);
    }

    @Override
    @Transactional
    public void updateProject(ProjectDto projectDto) {
        log.trace("Updating project: " + projectDto);
        final ProjectEntity projectEntity = findEntityById(projectDto.getId());
        final ProjectEntity changedProjectEntity = mapper.map(projectDto, projectEntity);
        projectRepository.save(changedProjectEntity);
        log.trace("Project updated: " + changedProjectEntity);
    }

    @Override
    @Transactional
    public void removeProject(String projectId) {
        final ProjectEntity entityById = findEntityById(projectId);
        projectRepository.delete(entityById);
        log.trace("Project removed : " + projectId);
    }

    @Override
    public ProjectDto findById(String projectId) {
        final ProjectEntity entity = findEntityById(projectId);

        return mapper.map(entity, new ProjectDto());
    }

    @Override
    public ProjectEntity findEntityById(String projectId) {
        final Optional<ProjectEntity> byId = projectRepository
                .findById(projectId);

        String resultForLog = byId
                .map(projectEntity -> "Found project wid id: " + projectEntity.getId() + " || " + projectEntity)
                .orElseGet(() -> "Project wih id: " + projectId + " not found.");
        log.trace(resultForLog);

        return byId
                .orElseThrow(() -> new NotFoundException("Project with key: " + projectId + " not found."));
    }

    @Override
    public Page<ProjectDto> findAllByAssignedUserId(String userId, Pageable pageable) {
        log.trace("Searching projects by attached worker. userId : " + userId);
        final UserEntity userEntity = userService.findEntityById(userId);
        final Page<UserProjectEntity> userProjectEntities = userProjectRepository
                .findAllByUserEntity(userEntity, pageable);
        log.trace("Found " + userProjectEntities.getTotalElements() + " projects, where user is: " + userId);
        return userProjectEntities
                .map(entity -> mapper.map(entity.getProjectEntity(), new ProjectDto()));
    }

    @Override
    @Transactional
    public void attachUserToProject(UserProjectDto userProjectDto) {
        final UserEntity userEntity = userService.findByEmail(userProjectDto.getEmail());
        final ProjectEntity projectEntity = findEntityById(userProjectDto.getProjectId());
        final UserProjectEntity userProjectEntity = new UserProjectEntity();
        userProjectEntity.setUserEntity(userEntity);
        userProjectEntity.setProjectEntity(projectEntity);
        userProjectEntity.setRole(userProjectDto.getRole());
        userProjectRepository.save(userProjectEntity);
    }

    @Override
    public List<ProjectDto> search(String text) {
        return projectRepository.findAllByNameContainsOrIdContains(text, text).stream()
                .map(projectEntity -> mapper.map(projectEntity, new ProjectDto())).collect(Collectors.toList());
    }

}

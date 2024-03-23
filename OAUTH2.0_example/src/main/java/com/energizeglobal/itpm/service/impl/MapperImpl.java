package com.energizeglobal.itpm.service.impl;

import com.energizeglobal.itpm.model.*;
import com.energizeglobal.itpm.model.dto.*;
import com.energizeglobal.itpm.repository.ProjectVersionRepository;
import com.energizeglobal.itpm.service.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class MapperImpl implements Mapper {
    private final UserService userService;
    private final SprintService sprintService;
    private final TaskService taskService;
    private final ProjectService projectService;
    private final ProjectVersionService projectVersionService;
    private final ProjectVersionRepository projectVersionRepository;


    public MapperImpl(@Lazy UserService userService,
                      @Lazy SprintService sprintService,
                      @Lazy TaskService taskService,
                      @Lazy ProjectService projectService,
                      @Lazy ProjectVersionService projectVersionService,
                      ProjectVersionRepository projectVersionRepository) {

        this.userService = userService;
        this.sprintService = sprintService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.projectVersionService = projectVersionService;
        this.projectVersionRepository = projectVersionRepository;
    }

    @Override
    public UserEntity map(UserDto userDto, UserEntity userEntity) {
        userEntity.setId(userDto.getUserId());
        userEntity.setEmail(userDto.getEmail().toLowerCase());

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        userEntity.setIsActive(userDto.getIsActive());

        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }

    @Override
    public UserDto map(UserEntity userEntity, UserDto userDto) {
        userDto.setUserId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail().toLowerCase());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setIsActive(userEntity.getIsActive());
        userDto.setRegistrationDate(userEntity.getRegistrationDate());
        return userDto;
    }

    @Override
    public CommentEntity map(CommentDto commentDto, CommentEntity commentEntity) {
        commentEntity.setId(commentDto.getId());
        commentEntity.setPublisherUserEntity(userService.findEntityById(commentDto.getPublisherId()));
        commentEntity.setTaskEntity(taskService.findEntityById(commentDto.getTaskId()));
        commentEntity.setText(commentDto.getText());

        return commentEntity;
    }

    @Override
    public CommentDto map(CommentEntity commentEntity, CommentDto commentDto) {
        commentDto.setId(commentEntity.getId());
        commentDto.setPublisherId(commentEntity.getPublisherUserEntity().getId());
        commentDto.setTaskId(commentEntity.getTaskEntity().getId());
        commentDto.setText(commentEntity.getText());
        commentDto.setCreatedAt(commentEntity.getCreatedAt());
        return commentDto;
    }

    @Override
    public ProjectEntity map(ProjectDto projectDto, ProjectEntity projectEntity) {
        projectEntity.setId(projectDto.getId());
        projectEntity.setName(projectDto.getName());
        projectEntity.setDescription(projectDto.getDescription());
        projectEntity.setCreator(userService.findEntityById(projectDto.getCreatorId()));
        return projectEntity;
    }

    @Override
    public ProjectDto map(ProjectEntity projectEntity, ProjectDto projectDto) {
        projectDto.setId(projectEntity.getId());
        projectDto.setName(projectEntity.getName());
        projectDto.setDescription(projectEntity.getDescription());
        projectDto.setCreatedAt(projectEntity.getCreatedAt());
        projectDto.setCreatorId(projectEntity.getCreator().getId());
        return projectDto;
    }

    @Override
    public SprintEntity map(SprintDto sprintDto, SprintEntity sprintEntity) {
        sprintEntity.setId(sprintDto.getId());
        sprintEntity.setProjectEntity(projectService.findEntityById(sprintDto.getProjectId()));
        sprintEntity.setCreatorUserEntity(userService.findEntityById(sprintDto.getCreatorId()));
        sprintEntity.setDeadLine(sprintDto.getDeadLine());
        sprintEntity.setName(sprintDto.getName());
        sprintEntity.setStartDate(sprintDto.getStartDate());
        sprintEntity.setDeadLine(sprintDto.getDeadLine());
        sprintEntity.setIsRunning(sprintDto.getIsRunning());
        sprintEntity.setIsFinished(sprintDto.getIsFinished());
        return sprintEntity;
    }

    @Override
    public SprintDto map(SprintEntity source, SprintDto sprintDto) {
        sprintDto.setId(source.getId());
        sprintDto.setProjectId(source.getProjectEntity().getId());
        sprintDto.setCreatorId(source.getCreatorUserEntity().getId());
        sprintDto.setCreationTimestamp(source.getCreationTimestamp());
        sprintDto.setDeadLine(source.getDeadLine());
        sprintDto.setName(source.getName());
        sprintDto.setStartDate(source.getStartDate());
        sprintDto.setDeadLine(source.getDeadLine());
        sprintDto.setIsRunning(source.getIsRunning());
        sprintDto.setIsFinished(source.getIsFinished());
        return sprintDto;
    }

    @Override
    public TaskEntity map(TaskDto taskDto, TaskEntity taskEntity) {
        taskEntity.setId(taskDto.getId());
        taskEntity.setCreatorUserEntity(userService.findEntityById(taskDto.getCreatorId()));
        if (taskDto.getAssignedUserId() != null && !taskDto.getAssignedUserId().isEmpty()) {
            taskEntity.setAssignedUserEntity(userService.findEntityById(taskDto.getAssignedUserId()));
        }
        taskEntity.setProjectEntity(projectService.findEntityById(taskDto.getProjectId()));
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setName(taskDto.getName());
        if (taskDto.getSprintId() != null) {
            taskEntity.setSprintEntity(sprintService.findEntityById(taskDto.getSprintId()));
        }
        taskEntity.setTaskType(taskDto.getTaskType());
        taskEntity.setTaskState(taskDto.getTaskState());
        taskEntity.setPriority(taskDto.getPriority());
        taskEntity.setProjectVersionEntity(projectVersionService
                .findEntityById(taskDto.getProjectVersionId()));

        if (taskDto.getAffectedProjectVersions() != null && taskDto.getAffectedProjectVersions().length != 0) {
            final List<Long> projectIdList = Arrays.stream(taskDto.getAffectedProjectVersions()).map(Long::valueOf).collect(Collectors.toList());
            final List<ProjectVersionEntity> versionEntityList = projectVersionRepository.findAllById(projectIdList);
            taskEntity.setAffectedProjectVersions(versionEntityList);
        }

        if (taskDto.getParentId() != null) {
            taskEntity.setParent(taskService.findEntityById(taskDto.getParentId()));
        }

        if (taskDto.getTriggeredById() != null) {
            taskEntity.setTriggerType(taskDto.getTriggerType());
            final TaskEntity triggerTaskEntity = taskService.findEntityById(taskDto.getTriggeredById());
            taskEntity.setTriggeredBy(triggerTaskEntity);

        }

        return taskEntity;
    }

    @Override
    public TaskDto map(TaskEntity taskEntity, TaskDto taskDto) {
        taskDto.setId(taskEntity.getId());
        taskDto.setName(taskEntity.getName());
        taskDto.setDescription(taskEntity.getDescription());
        if (taskEntity.getSprintEntity() != null) {
            taskDto.setSprintId(taskEntity.getSprintEntity().getId());
        }
        taskDto.setCreatorId(taskEntity.getCreatorUserEntity().getId());
        if (taskEntity.getAssignedUserEntity() != null) {
            taskDto.setAssignedUserId(taskEntity.getAssignedUserEntity().getId());
        }
        taskDto.setProjectId(taskEntity.getProjectEntity().getId());
        taskDto.setTaskType(taskEntity.getTaskType());
        taskDto.setTaskState(taskEntity.getTaskState());
        taskDto.setPriority(taskEntity.getPriority());
        taskDto.setProjectVersionId(taskEntity.getProjectVersionEntity().getId());

        if (!taskEntity.getAffectedProjectVersions().isEmpty()) {

            taskDto.setAffectedProjectVersions(
                    taskEntity
                            .getAffectedProjectVersions()
                            .stream()
                            .map(projectVersionEntity -> "" + projectVersionEntity.getId())
                            .toArray(String[]::new)
            );
        }

        if (taskEntity.getParent() != null) {
            taskDto.setParentId(taskEntity.getParent().getId());
        }


        if (taskEntity.getTriggeredBy() != null) {
            taskDto.setTriggerType(taskEntity.getTriggerType());
            taskDto.setTriggeredById(taskEntity.getTriggeredBy().getId());
        }

        return taskDto;
    }

    @Override
    public UserProjectEntity map(UserProjectDto userProjectDto, UserProjectEntity userProjectEntity) {
        userProjectEntity.setId(userProjectDto.getId());
        userProjectEntity.setUserEntity(userService.findEntityById(userProjectDto.getUserId()));
        userProjectEntity.setProjectEntity(projectService.findEntityById(userProjectDto.getProjectId()));
        userProjectEntity.setRole(userProjectDto.getRole());
        return userProjectEntity;
    }

    @Override
    public UserProjectDto map(UserProjectEntity userProjectEntity, UserProjectDto userProjectDto) {
        userProjectDto.setId(userProjectEntity.getId());

        userProjectDto.setUserId(userProjectEntity.getUserEntity().getId());
        userProjectDto.setEmail(userProjectEntity.getUserEntity().getEmail());
        userProjectDto.setFirstName(userProjectEntity.getUserEntity().getFirstName());
        userProjectDto.setLastName(userProjectEntity.getUserEntity().getLastName());
        userProjectDto.setIsActive(userProjectEntity.getUserEntity().getIsActive());

        userProjectDto.setProjectId(userProjectEntity.getProjectEntity().getId());
        userProjectDto.setRole(userProjectEntity.getRole());
        return userProjectDto;
    }

    @Override
    public ProjectVersionEntity map(ProjectVersionDto versionDto, ProjectVersionEntity versionEntity) {
        versionEntity.setId(versionDto.getId());
        versionEntity.setProjectEntity(projectService.findEntityById(versionDto.getProjectId()));
        versionEntity.setVersion(versionDto.getVersion());
        versionEntity.setVersionStatus(versionDto.getVersionStatus());
        return versionEntity;
    }

    @Override
    public ProjectVersionDto map(ProjectVersionEntity versionEntity, ProjectVersionDto versionDto) {
        versionDto.setId(versionEntity.getId());
        versionDto.setProjectId(versionEntity.getProjectEntity().getId());
        versionDto.setVersion(versionEntity.getVersion());
        versionDto.setVersionStatus(versionEntity.getVersionStatus());
        versionDto.setRegistrationDate(versionEntity.getRegistrationDate());
        return versionDto;
    }


    @Override
    public FileInfoDto map(FileEntity fileEntity, FileInfoDto fileInfoDto) {
        fileInfoDto.setId(fileEntity.getId());
        fileInfoDto.setFileName(fileEntity.getFileName());
        fileInfoDto.setCreatedAt(fileEntity.getCreatedAt());
        fileInfoDto.setTaskId(fileEntity.getOwnerTaskEntity().getId());
        return fileInfoDto;
    }
}

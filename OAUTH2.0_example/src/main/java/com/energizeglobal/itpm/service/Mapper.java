package com.energizeglobal.itpm.service;

import com.energizeglobal.itpm.model.*;
import com.energizeglobal.itpm.model.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface Mapper {
    UserEntity map(UserDto userDto, UserEntity userEntity);

    UserDto map(UserEntity userEntity, UserDto userDto);

    CommentEntity map(CommentDto commentDto, CommentEntity commentEntity);

    CommentDto map(CommentEntity commentEntity, CommentDto commentDto);

    ProjectEntity map(ProjectDto projectDto, ProjectEntity projectEntity);

    ProjectDto map(ProjectEntity projectEntity, ProjectDto projectDto);

    SprintEntity map(SprintDto sprintDto, SprintEntity sprintEntity);

    SprintDto map(SprintEntity sprintEntity, SprintDto sprintDto);


    TaskEntity map(TaskDto taskDto, TaskEntity taskEntity);

    TaskDto map(TaskEntity taskEntity, TaskDto taskDto);

    UserProjectEntity map(UserProjectDto userProjectDto, UserProjectEntity userProjectEntity);

    UserProjectDto map(UserProjectEntity userProjectEntity, UserProjectDto userProjectDto);

    ProjectVersionEntity map(ProjectVersionDto versionDto, ProjectVersionEntity versionEntity);

    ProjectVersionDto map(ProjectVersionEntity versionEntity, ProjectVersionDto versionDto);

    FileInfoDto map(FileEntity fileEntity, FileInfoDto fileInfoDto);
}

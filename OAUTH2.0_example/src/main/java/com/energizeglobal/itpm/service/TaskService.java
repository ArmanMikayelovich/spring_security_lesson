package com.energizeglobal.itpm.service;

import com.energizeglobal.itpm.model.ProjectEntity;
import com.energizeglobal.itpm.model.TaskEntity;
import com.energizeglobal.itpm.model.UserEntity;
import com.energizeglobal.itpm.model.dto.TaskDto;
import com.energizeglobal.itpm.model.enums.TaskState;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TaskService {
    TaskDto findById(Long taskId);

    TaskEntity findEntityById(Long taskId);

    List<TaskDto> findAllBySprintAndState(Long sprintId, TaskState taskState);

  TaskDto addTask(TaskDto taskDto);

    void attachTaskToUser(Long taskId, String userId);

    void changeTaskState(Long taskId, TaskState taskState);

    void changeTask(TaskDto taskDto);

    void remove(Long taskId);

    void changeTaskPriority(TaskDto taskDto);

    List<TaskDto> findAllByUserAndProject(UserEntity userEntity, ProjectEntity projectEntity, Sort sort);

    List<TaskDto> findAllSubTasks(Long taskId);

    List<TaskDto> findAllByProjectId(String projectId);

    void cloneTask(TaskDto taskDto);

    void moveTaskToAnotherProject(TaskDto taskDto);


    List<TaskDto> findAllFreeTasksOfProject(String projectId, Sort sort);

    void attachTaskToSprint(TaskDto taskDto);

    void detachTaskFromSprint(Long taskId);

    List<TaskDto> findAllBySprintId(Long sprintId);

    Map<String, List<TaskDto>> getUsersTasksInProject(String userId, String projectId, Sort sort);


    List<TaskDto> search(String searchString);

    List<TaskDto> searchInProject(String text, String projectId);
}

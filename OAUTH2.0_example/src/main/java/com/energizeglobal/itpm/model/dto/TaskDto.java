package com.energizeglobal.itpm.model.dto;

import com.energizeglobal.itpm.model.enums.TaskPriority;
import com.energizeglobal.itpm.model.enums.TaskState;
import com.energizeglobal.itpm.model.enums.TaskTrigger;
import com.energizeglobal.itpm.model.enums.TaskType;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;

    private String name;

    private TaskType taskType;

    private TaskState taskState;

    private TaskPriority priority;

    private String description;

    private String creatorId;

    private String assignedUserId;

    private Long sprintId;

    private String projectId;

    private Long projectVersionId;

    private String[] affectedProjectVersions;

    private Long parentId;

    private TaskTrigger triggerType;

    private Long triggeredById;
}

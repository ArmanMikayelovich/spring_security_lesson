package com.energizeglobal.itpm.util;

import com.energizeglobal.itpm.model.dto.TaskDto;
import com.energizeglobal.itpm.model.enums.TaskPriority;

import java.util.Comparator;

public class TaskPriorityComparator implements Comparator<TaskDto> {

    public int compare(TaskDto a, TaskDto b) {

        if (a.getPriority() == TaskPriority.HIGH &&
                (b.getPriority() == TaskPriority.DEFAULT || b.getPriority() == TaskPriority.LOW)) {
            return -1;
        } else if (b.getPriority() == TaskPriority.HIGH &&
                (a.getPriority() == TaskPriority.DEFAULT || a.getPriority() == TaskPriority.LOW)) {
            return 1;
        } else if (a.getPriority() == TaskPriority.DEFAULT && b.getPriority() == TaskPriority.LOW) {
            return -1;
        } else if (b.getPriority() == TaskPriority.DEFAULT && a.getPriority() == TaskPriority.LOW) {
            return 1;
        }
        return 0;
    }
}


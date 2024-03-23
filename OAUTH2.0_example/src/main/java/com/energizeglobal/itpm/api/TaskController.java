package com.energizeglobal.itpm.api;

import com.energizeglobal.itpm.model.dto.FileInfoDto;
import com.energizeglobal.itpm.model.dto.TaskDto;
import com.energizeglobal.itpm.model.enums.TaskState;
import com.energizeglobal.itpm.service.FileService;
import com.energizeglobal.itpm.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private static final Logger log = Logger.getLogger(TaskController.class);

    private final TaskService taskService;
    private final FileService fileService;

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/{taskId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TaskDto findById(@PathVariable("taskId") Long taskId) {
        log.trace("searching task by id: " + taskId);
        return taskService.findById(taskId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/by-sprint/{sprintId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TaskDto> findBySprintIdAndState(@PathVariable("sprintId") Long sprintId,
                                                @RequestParam(required = false) TaskState taskState) {
        log.trace("searching all tasks by Sprint: " + sprintId + " and state: " + taskState);
        if (taskState != null) {
            return taskService.findAllBySprintAndState(sprintId, taskState);
        } else {
            return taskService.findAllBySprintId(sprintId);
        }


    }

    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        log.trace("adding task in sprint: " + taskDto);
        return taskService.addTask(taskDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/{taskId}/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void attachFile(MultipartFile file, @PathVariable Long taskId) {
        fileService.saveFile(file, taskId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/{taskId}/delete-file/{fileId}")
    public void deleteFile(@PathVariable Long taskId, @PathVariable Long fileId) {
        fileService.deleteFile(fileId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/{taskId}/get-files", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FileInfoDto> getFileInfosOfTask(@PathVariable("taskId") Long taskId) {
        return fileService.getAllByTaskId(taskId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/{taskId}/get-files/{fileId}")
    public byte[] getFileById(@PathVariable Long taskId, @PathVariable Long fileId, HttpServletResponse response) {
        log.trace("searching file of task: " + taskId + " with file id: " + fileId);
        final byte[] file = fileService.getFile(fileId);
        final FileInfoDto infoDto = fileService.findById(fileId);
        response.addHeader("Content-Disposition", "attachment; filename=" + infoDto.getFileName());
        return file;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateTask(@RequestBody TaskDto taskDto) {

        log.trace("updating task: " + taskDto);
        taskService.changeTask(taskDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "change-priority", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changeTaskPriority(@RequestBody TaskDto taskDto) {
        log.trace("changing priority of task: " + taskDto.getId() + " to : " + taskDto.getPriority());
        taskService.changeTaskPriority(taskDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/change-state", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changeTaskState(@RequestBody TaskDto taskDto) {
        log.trace("changing state of task : " + taskDto.getId() + "to :" + taskDto.getTaskState());
        taskService.changeTaskState(taskDto.getId(), taskDto.getTaskState());
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/attach", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void attachTaskToUser(@RequestBody TaskDto taskDto) {
        log.trace("attaching task: " + taskDto.getId() + " to user: " + taskDto.getAssignedUserId());
        taskService.attachTaskToUser(taskDto.getId(), taskDto.getAssignedUserId());
    }

    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping("/{taskId}")
    public void delete(@PathVariable("taskId") Long taskId) {
        log.trace("removing task with id: " + taskId);
        taskService.remove(taskId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("/{taskId}/sub-tasks")
    public List<TaskDto> getSubTasks(@PathVariable("taskId") Long taskId) {
        return taskService.findAllSubTasks(taskId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/by-project/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getTasksByProject(@PathVariable("projectId") String projectId) {
        log.trace("searching all tasks of project : " + projectId);
        return taskService.findAllByProjectId(projectId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/clone", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void cloneTaskToProject(@RequestBody TaskDto taskDto) {
        log.trace("cloning task: " + taskDto.getId() + " to project: " + taskDto.getProjectId());
        taskService.cloneTask(taskDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/move", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void moveTaskToAnotherProject(TaskDto taskDto) {
        log.trace("moving task: " + taskDto.getId() + " to project: " + taskDto.getProjectId());
        taskService.moveTaskToAnotherProject(taskDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/by-project/{projectId}/free")
    public List<TaskDto> findAllFreeTasksOfProject(@PathVariable("projectId") String projectId,
                                                   @RequestParam(name = "sort", required = false) String sortProperty,
                                                   @RequestParam(required = false) String direction) {
        log.trace("searching all free tasks of project: " + projectId);

        if (sortProperty == null && direction == null) {
            return taskService.findAllFreeTasksOfProject(projectId, Sort.unsorted());
        }

        final Sort sort = Sort.by(Sort.Direction.fromString(direction), sortProperty);
        return taskService.findAllFreeTasksOfProject(projectId, sort);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/attach-to-sprint", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void attachTaskToSprint(@RequestBody TaskDto taskDto) {
        log.trace("attaching task: " + taskDto.getId() + "to sprint: " + taskDto.getSprintId());
        taskService.attachTaskToSprint(taskDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/detach-from-sprint/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void detachTaskFromSprint(@PathVariable("taskId") Long taskId) {
        log.trace("detaching task: " + taskId + "from sprint");
        taskService.detachTaskFromSprint(taskId);
    }
}

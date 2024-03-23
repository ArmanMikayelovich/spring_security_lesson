package com.energizeglobal.itpm.api;

import com.energizeglobal.itpm.model.dto.ProjectDto;
import com.energizeglobal.itpm.model.dto.SprintDto;
import com.energizeglobal.itpm.model.dto.TaskDto;
import com.energizeglobal.itpm.model.dto.UserDto;
import com.energizeglobal.itpm.service.ProjectService;
import com.energizeglobal.itpm.service.SprintService;
import com.energizeglobal.itpm.service.TaskService;
import com.energizeglobal.itpm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SearchController {
    private static final String USERS = "users";
    private static final String PROJECTS = "projects";
    private static final String SPRINTS = "sprints";
    private static final String TASKS = "tasks";

    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;
    private final SprintService sprintService;


    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Set> search(@RequestParam("searchText") String searchText, @RequestParam(required = false) String projectId) {
        final String[] searchTexts = searchText.trim().split(" ");
        final HashMap<String, Set> searchResults = new HashMap<>();

        searchResults.put(USERS, new HashSet<UserDto>());
        searchResults.put(PROJECTS, new HashSet<ProjectDto>());
        searchResults.put(SPRINTS, new HashSet<SprintDto>());
        searchResults.put(TASKS, new HashSet<TaskDto>());

        if (projectId == null) {

            for (String text : searchTexts) {
                final List<UserDto> userDtos = userService.search(searchText);
                if (!userDtos.isEmpty()) {
                    searchResults.get(USERS).addAll(userDtos);
                }
                final List<ProjectDto> projectDtos = projectService.search(text);
                if (!projectDtos.isEmpty()) {
                    searchResults.get(PROJECTS).addAll(projectDtos);
                }

                final List<SprintDto> sprintDtos = sprintService.search(text);
                if (!sprintDtos.isEmpty()) {
                    searchResults.get(SPRINTS).addAll(sprintDtos);
                }

                final List<TaskDto> taskDtos = taskService.search(text);
                if (!taskDtos.isEmpty()) {
                    searchResults.get(TASKS).addAll(taskDtos);
                }
            }
        } else {

            for (String text : searchTexts) {
                final List<UserDto> userDtos = userService.searchInProject(searchText, projectId);
                if (!userDtos.isEmpty()) {
                    searchResults.get(USERS).addAll(userDtos);
                }
                final List<ProjectDto> projectDtos = projectService.search(text);
                if (!projectDtos.isEmpty()) {
                    searchResults.get(PROJECTS).addAll(projectDtos);
                }

                final List<SprintDto> sprintDtos = sprintService.searchInProject(text, projectId);
                if (!sprintDtos.isEmpty()) {
                    searchResults.get(SPRINTS).addAll(sprintDtos);
                }

                final List<TaskDto> taskDtos = taskService.searchInProject(text, projectId);
                if (!taskDtos.isEmpty()) {
                    searchResults.get(TASKS).addAll(taskDtos);
                }
            }
        }
        return searchResults;
    }
}

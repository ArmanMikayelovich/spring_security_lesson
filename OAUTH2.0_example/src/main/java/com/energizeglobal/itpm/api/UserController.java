package com.energizeglobal.itpm.api;

import com.energizeglobal.itpm.model.CustomOAuth2User;
import com.energizeglobal.itpm.model.dto.TaskDto;
import com.energizeglobal.itpm.model.dto.UserDto;
import com.energizeglobal.itpm.model.dto.UserProjectDto;
import com.energizeglobal.itpm.service.TaskService;
import com.energizeglobal.itpm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);

    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("/user")
    @PreAuthorize(value = "isAuthenticated()")
    public UserDto user(@AuthenticationPrincipal AuthenticatedPrincipal principal, HttpServletRequest request) {
        final String userId = ((CustomOAuth2User) principal).getId();
        return userService.findById(userId);
    }

    @GetMapping("/user-info")
    public AuthenticatedPrincipal userInfo(@AuthenticationPrincipal OAuth2User principal) {
        return principal;
    }

    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createUser(@RequestBody UserDto userDto) {
        log.trace("creating user:" + userDto);
        userService.createUser(userDto);
        log.trace("user created:" + userDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateUser(@RequestBody UserDto userDto) {
        log.trace("updating user:" + userDto);
        userService.updateUser(userDto);
        log.trace("user updated:" + userDto);

    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto findById(@PathVariable("userId") String userId) {
        log.trace("searching user by id: " + userId);
        return userService.findById(userId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/activation")
    public void changeActivation(@RequestParam("userId") String userId,
                                 @RequestParam("status") Boolean status) {
        log.trace("changing activation status of user:" + userId + " to: " + status);
        userService.changeActivationStatus(userId, status);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/by-project/{projectId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<UserProjectDto> findAllByProjectId(@PathVariable("projectId") String projectId,
                                                   @RequestParam(required = false) final Pageable pageable) {
        log.trace("searching users by project id: " + projectId + " || pagination: " + pageable);
        return userService.findAllUsersByProject(projectId, pageable);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/{userId}/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserProjectDto> findAllProjectsOfUser(@PathVariable("userId") String userId) {
        return userService.findAllProjectsOfUser(userId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/{userId}/projects/{projectId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<TaskDto>> getUserTasksInProject(@PathVariable("userId") String userId,
                                                            @PathVariable("projectId") String projectId,
                                                            @RequestParam(name = "sort", required = false)
                                                                    String sortProperty,
                                                            @RequestParam(required = false) String direction) {

        if (sortProperty == null || direction == null) {
            return taskService.getUsersTasksInProject(userId, projectId, Sort.unsorted());
        } else {
            Sort sort = Sort.by(Sort.Direction.fromString(direction), sortProperty);
            return taskService.getUsersTasksInProject(userId, projectId, sort);
        }


    }
}

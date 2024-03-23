package com.energizeglobal.itpm.api;

import com.energizeglobal.itpm.model.dto.SprintDto;
import com.energizeglobal.itpm.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sprints")
@RequiredArgsConstructor
public class SprintController {
    private static final Logger log = Logger.getLogger(SprintController.class);

    private final SprintService sprintService;

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/{sprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SprintDto getById(@PathVariable("sprintId") Long sprintId) {
        log.trace("searching sprint by id: " + sprintId);
        return sprintService.findById(sprintId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addSprint(@RequestBody SprintDto sprintDto) {

        log.trace("creating Sprint: " + sprintDto);
        sprintService.addSprintToProject(sprintDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/by-project/{projectId}/not-finished", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SprintDto> getAllNotFinishedSprintsOfProject(@PathVariable("projectId") String projectId) {
        log.trace("Searching all not finished sprints of project: " + projectId);
        return sprintService.findAllSprintWhichNotFinished(projectId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void changeDeadline(@RequestBody SprintDto sprintDto) {

        log.trace("Changing deadline of sprint with id: " + sprintDto
                + " to " + sprintDto.getDeadLine());
        sprintService.changeDeadLine(sprintDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/by-project/{projectId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<SprintDto> findAllByProjectId(@PathVariable("projectId") String projectId,
                                              @RequestParam(required = false) Pageable pageable) {
        log.trace("searching sprints by project id: " + projectId + " || pageable: " + pageable);
        return sprintService.findAllSprintsByProjectId(projectId, pageable);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/by-project/{projectId}/active", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SprintDto findActiveSprintOfProject(@PathVariable("projectId") String projectId) {
        log.trace("Searching active sprint of project: " + projectId);
        return sprintService.findActiveSprintByProjectId(projectId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void startSprint(@RequestBody SprintDto sprintDto) {
        log.trace("starting sprint: " + sprintDto);
        sprintService.startSprint(sprintDto);
    }
}

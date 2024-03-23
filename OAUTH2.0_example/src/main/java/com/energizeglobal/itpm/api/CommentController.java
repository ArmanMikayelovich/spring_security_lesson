package com.energizeglobal.itpm.api;

import com.energizeglobal.itpm.model.dto.CommentDto;
import com.energizeglobal.itpm.service.CommentService;
import com.energizeglobal.itpm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class CommentController {
    private static final Logger log = Logger.getLogger(CommentController.class);

    private final CommentService commentService;
    private final UserService userService;

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/comments/{commentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CommentDto findById(@PathVariable("commentId") Long commentId) {
        log.trace("Searching comment by id: " + commentId);
        return commentService.findById(commentId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "{tasksId}/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<CommentDto> findAllByTaskId(@PathVariable("tasksId") Long tasksId,
                                            @RequestParam(required = false) Pageable pageable) {
        log.trace("Searching comments  by taskId: " + tasksId);
        return commentService.findAllByTaskId(tasksId, pageable);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/{taskId}/comments",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addComment(@PathVariable("taskId") Long taskId, @RequestBody CommentDto commentDto) {
        commentDto.setTaskId(taskId);
        log.trace("adding comment: " + commentDto);
        commentService.createComment(commentDto);

        for (String userId : commentDto.getNotificationUsers()) {
            userService.sendMailNotificationOfComment(userId, commentDto.getTaskId());
        }

    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/{taskId}/comments",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateComment(@PathVariable("taskId") Long taskId, @RequestBody CommentDto commentDto) {
        commentDto.setTaskId(taskId);
        log.trace("updating comment: " + commentDto);
        commentService.updateComment(commentDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/comments/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId) {
        log.trace("Removing comment: " + commentId);
        commentService.delete(commentId);
    }
}

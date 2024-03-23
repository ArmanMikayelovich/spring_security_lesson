package com.energizeglobal.itpm.service.impl;

import com.energizeglobal.itpm.model.CommentEntity;
import com.energizeglobal.itpm.model.TaskEntity;
import com.energizeglobal.itpm.model.dto.CommentDto;
import com.energizeglobal.itpm.repository.CommentRepository;
import com.energizeglobal.itpm.service.CommentService;
import com.energizeglobal.itpm.service.Mapper;
import com.energizeglobal.itpm.service.TaskService;
import com.energizeglobal.itpm.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private static final Logger log = Logger.getLogger(CommentServiceImpl.class);

    private final Mapper mapper;
    private final CommentRepository commentRepository;
    private final TaskService taskService;

    @Override
    @Transactional
    public void createComment(CommentDto commentDto) {
        log.trace("creating comment: " + commentDto);
        commentDto.setId(null);
        CommentEntity commentEntity = mapper.map(commentDto, new CommentEntity());
        commentEntity = commentRepository.save(commentEntity);
        log.trace("comment created: " + commentEntity);
    }

    @Override
    @Transactional
    public void updateComment(CommentDto commentDto) {
        log.trace("Updating comment: " + commentDto);
        CommentEntity commentEntity = findEntityById(commentDto.getId());
        mapper.map(commentDto, commentEntity);
        commentEntity = commentRepository.save(commentEntity);
        log.trace(("comment updated: " + commentEntity));
    }

    @Override
    @Transactional
    public void delete(Long commentId) {
        final CommentEntity commentEntity = findEntityById(commentId);
        commentRepository.delete(commentEntity);
        log.trace("Comment deleted: " + commentId);
    }

    @Override
    public CommentDto findById(Long commentId) {
        final CommentEntity commentEntity = findEntityById(commentId);
        return mapper.map(commentEntity, new CommentDto());
    }

    @Override
    public CommentEntity findEntityById(Long commentId) {
        log.trace("Searching Comment by id: " + commentId);

        try {
            return commentRepository
                    .findById(commentId)
                    .orElseThrow(() -> new NotFoundException("Comment with id: " + commentId + " not found."));
        } catch (Exception exception) {
            log.error("Error in searching comment by id: " + commentId
                    + " \n Error message: " + exception.getMessage());
            throw exception;
        }
    }

    @Override
    public Page<CommentDto> findAllByTaskId(Long taskId, Pageable pageable) {
        final TaskEntity taskEntity = taskService.findEntityById(taskId);
        log.trace("Searching comments by task id: " + taskId);
        final Page<CommentEntity> commentEntityPage = commentRepository
                .findAllByTaskEntity(taskEntity, pageable);
        log.trace("Found comments by task id: " + taskId + "|| count " + commentEntityPage.getTotalElements());
        return commentEntityPage
                .map(c -> mapper.map(c, new CommentDto()));
    }

}

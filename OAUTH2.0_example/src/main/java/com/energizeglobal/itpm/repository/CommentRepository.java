package com.energizeglobal.itpm.repository;

import com.energizeglobal.itpm.model.CommentEntity;
import com.energizeglobal.itpm.model.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    Page<CommentEntity> findAllByTaskEntity(TaskEntity taskEntity, Pageable pageable);
}

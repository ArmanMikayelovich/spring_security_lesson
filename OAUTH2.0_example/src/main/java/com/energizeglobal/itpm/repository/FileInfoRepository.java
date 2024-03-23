package com.energizeglobal.itpm.repository;

import com.energizeglobal.itpm.model.FileEntity;
import com.energizeglobal.itpm.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileInfoRepository extends JpaRepository<FileEntity, Long> {

    List<FileEntity> findAllByOwnerTaskEntityAndIsDeletedFalse(TaskEntity taskEntity);

}

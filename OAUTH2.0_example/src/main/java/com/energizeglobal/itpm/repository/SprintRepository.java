package com.energizeglobal.itpm.repository;

import com.energizeglobal.itpm.model.ProjectEntity;
import com.energizeglobal.itpm.model.SprintEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SprintRepository extends JpaRepository<SprintEntity, Long> {
    Page<SprintEntity> findAllByProjectEntity(ProjectEntity projectEntity, Pageable pageable);

    Optional<SprintEntity> findByProjectEntityAndIsRunningTrue(ProjectEntity projectEntity);

    List<SprintEntity> findAllByProjectEntityAndIsFinishedFalse(ProjectEntity projectEntity);

    List<SprintEntity> findAllByNameContains(String searchString);

    List<SprintEntity> findAllByNameContainsAndProjectEntity(String text, ProjectEntity projectEntity);
}

package com.energizeglobal.itpm.repository;

import com.energizeglobal.itpm.model.ProjectEntity;
import com.energizeglobal.itpm.model.SprintEntity;
import com.energizeglobal.itpm.model.TaskEntity;
import com.energizeglobal.itpm.model.UserEntity;
import com.energizeglobal.itpm.model.enums.TaskState;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("select t from TaskEntity  t where t.sprintEntity=:sprintEntity and t.taskState=:taskState")
    List<TaskEntity> findAllBySprintAndState(@Param("sprintEntity") SprintEntity sprintEntity, @Param("taskState") TaskState taskState);

    List<TaskEntity> findAllBySprintEntity(SprintEntity sprintEntity);

    @Query("select t from TaskEntity t " +
            "where (t.projectEntity=:projectEntity " +
            "AND " +
            "(t.assignedUserEntity=:userEntity OR t.creatorUserEntity=:userEntity)) ")
    List<TaskEntity> findAllByUserAndProject(@Param("userEntity") UserEntity userEntity,
                                             @Param("projectEntity") ProjectEntity projectEntity, Sort sort);

    List<TaskEntity> findAllByParent(TaskEntity parent);

    List<TaskEntity> findAllByProjectEntity(ProjectEntity project);

    List<TaskEntity> findAllByProjectEntityAndSprintEntityNull(ProjectEntity projectEntity, Sort sort);

    List<TaskEntity> findAllByNameContains(String searchString);

    List<TaskEntity> findAllByNameContainsAndProjectEntity(String text, ProjectEntity project);
}

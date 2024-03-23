package com.energizeglobal.itpm.repository;

import com.energizeglobal.itpm.model.ProjectEntity;
import com.energizeglobal.itpm.model.UserEntity;
import com.energizeglobal.itpm.model.UserProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProjectEntity, Long> {

    Page<UserProjectEntity> findAllByProjectEntity(ProjectEntity projectEntity, Pageable pageable);

    Page<UserProjectEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);

    @Query("SELECT e FROM UserProjectEntity e " +
            " WHERE e.projectEntity=:project " +
            "AND " +
            "(e.userEntity.firstName LIKE %:text% " +
            "OR " +
            "e.userEntity.lastName LIKE %:text% " +
            "OR " +
            "e.userEntity.email LIKE %:text%)")
    List<UserProjectEntity> findAllBySearchTextAndProject(@Param("text") String text, @Param("project") ProjectEntity project);
}

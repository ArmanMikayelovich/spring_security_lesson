package com.energizeglobal.itpm.repository;

import com.energizeglobal.itpm.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {
    List<ProjectEntity> findAllByNameContainsOrIdContains(String searchText, String searchText2);
}

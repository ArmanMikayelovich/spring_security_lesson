package com.energizeglobal.itpm.model.dto;

import com.energizeglobal.itpm.model.enums.ProjectVersionStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectVersionDto {
    private Long id;

    private String projectId;

    private String version;

    private ProjectVersionStatus versionStatus;

    private LocalDate registrationDate;
}

package com.energizeglobal.itpm.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDto {

    private String id;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    private String creatorId;

}

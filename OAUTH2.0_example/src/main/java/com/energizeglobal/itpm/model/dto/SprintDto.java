package com.energizeglobal.itpm.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SprintDto {

    private Long id;

    private String name;

    private String projectId;

    private String creatorId;

    private LocalDateTime creationTimestamp;

    private LocalDate startDate;

    private LocalDate deadLine;

    private Boolean isRunning;

    private Boolean isFinished;


}

package com.energizeglobal.itpm.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileInfoDto {
    private Long id;

    private String fileName;

    private LocalDateTime createdAt;

    private Long taskId;
}

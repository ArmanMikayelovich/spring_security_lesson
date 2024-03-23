package com.energizeglobal.itpm.model.dto;

import com.energizeglobal.itpm.model.enums.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserProjectDto extends UserDto {
    private Long id;

    private String projectId;

    private UserRole role;
}


package com.energizeglobal.itpm.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Boolean isActive;

    private LocalDate registrationDate;
}

package com.energizeglobal.itpm.service;

import com.energizeglobal.itpm.model.RoleEntity;

public interface RoleService {
    void addRole(String name);

    void deleteRole(Long id);

    RoleEntity findByName(String roleName);
}

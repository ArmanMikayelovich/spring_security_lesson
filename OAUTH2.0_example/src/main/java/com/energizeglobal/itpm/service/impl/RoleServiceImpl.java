package com.energizeglobal.itpm.service.impl;

import com.energizeglobal.itpm.model.RoleEntity;
import com.energizeglobal.itpm.repository.RoleRepository;
import com.energizeglobal.itpm.service.RoleService;
import com.energizeglobal.itpm.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void addRole(String name) {
        final RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(name);
        roleRepository.save(roleEntity);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleEntity findByName(String roleName) {
        return roleRepository
                .findByRoleName(roleName)
                .orElseThrow(() -> new NotFoundException("Role with name: " + roleName + " not found."));
    }
}

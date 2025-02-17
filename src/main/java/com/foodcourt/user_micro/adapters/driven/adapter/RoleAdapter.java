package com.foodcourt.user_micro.adapters.driven.adapter;

import com.foodcourt.user_micro.adapters.driven.mapper.IRoleMapperEntity;
import com.foodcourt.user_micro.adapters.driven.repository.IRoleRepository;
import com.foodcourt.user_micro.domain.model.Role;
import com.foodcourt.user_micro.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleMapperEntity roleMapperEntity;

    @Override
    public Role findRoleByName(String name) {
        return roleMapperEntity.roleEntityToRole(roleRepository.findByName(name));
    }
}

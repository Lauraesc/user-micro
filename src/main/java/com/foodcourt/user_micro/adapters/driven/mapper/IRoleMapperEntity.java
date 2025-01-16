package com.foodcourt.user_micro.adapters.driven.mapper;


import com.foodcourt.user_micro.adapters.driven.entity.RoleEntity;
import com.foodcourt.user_micro.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IRoleMapperEntity {

    @Mapping(source = "name", target = "role")
    Role roleEntityToRole(RoleEntity roleEntity);
    @Mapping(source = "role", target = "name")
    RoleEntity roleToRoleEntity(Role role);


}

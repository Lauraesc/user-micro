package com.foodcourt.user_micro.adapters.driven.mapper;


import com.foodcourt.user_micro.adapters.driven.entity.UserEntity;
import com.foodcourt.user_micro.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IRoleMapperEntity.class})
public interface IUserMapperEntity {
    @Mapping(source = "role", target = "roleEntity")
    UserEntity userToUserEntity(User user);

    @Mapping(source = "roleEntity", target = "role")
    User userEntityToUser(UserEntity userEntity);
}

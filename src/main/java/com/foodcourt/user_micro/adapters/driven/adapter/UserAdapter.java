package com.foodcourt.user_micro.adapters.driven.adapter;

import com.foodcourt.user_micro.adapters.driven.mapper.IUserMapperEntity;
import com.foodcourt.user_micro.adapters.driven.repository.IUserRepository;
import com.foodcourt.user_micro.domain.model.User;
import com.foodcourt.user_micro.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserMapperEntity userMapperEntity;
    @Override
    public User saveUser(User user) {
        return userMapperEntity.userEntityToUser(userRepository.save(userMapperEntity.userToUserEntity(user)));
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return null;
    }
}

package com.foodcourt.user_micro.adapters.driven.adapter;

import com.foodcourt.user_micro.adapters.driven.entity.UserEntity;
import com.foodcourt.user_micro.adapters.driven.mapper.IUserMapperEntity;
import com.foodcourt.user_micro.adapters.driven.repository.IUserRepository;
import com.foodcourt.user_micro.domain.model.User;
import com.foodcourt.user_micro.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserMapperEntity userMapperEntity;
    @Override
    public User saveUser(User user) {

        UserEntity userEntity = userRepository.save(userMapperEntity.userToUserEntity(user));
        return userMapperEntity.userEntityToUser(userEntity);

    }

    @Override
    public Optional<User> existsUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapperEntity::userEntityToUser);
    }

    @Override
    public User getUserRole(Long id) {
        return userMapperEntity.userEntityToUser(userRepository.findById(id).get()); //con el .get() se obtiene el UserEntity del Optional<UserEntity>
    }
}

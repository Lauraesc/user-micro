package com.foodcourt.user_micro.domain.spi;

import com.foodcourt.user_micro.domain.model.User;

import java.util.Optional;

public interface  IUserPersistencePort {

    User saveUser(User user);
    Optional<User> existsUserByEmail(String email);
}

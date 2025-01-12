package com.foodcourt.user_micro.domain.spi;

import com.foodcourt.user_micro.domain.model.User;

public interface  IUserPersistencePort {

    User saveUser(User user);
    Boolean existsUserByEmail(String email);
}

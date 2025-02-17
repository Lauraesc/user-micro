package com.foodcourt.user_micro.domain.spi;

import com.foodcourt.user_micro.domain.model.User;

public interface IPasswordEncodePersistencePort {

    void encodePassword(User user);
    boolean checkPassword(String passwordLogin, String passwordUser);
}

package com.foodcourt.user_micro.domain.spi;

import com.foodcourt.user_micro.domain.model.User;

public interface ITokenPersistencePort {

    String getToken(User user);
}

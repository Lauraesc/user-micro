package com.foodcourt.user_micro.domain.spi;

import com.foodcourt.user_micro.domain.model.Auth;

public interface IAuthPersistencePort {

    void login (Auth auth);
}

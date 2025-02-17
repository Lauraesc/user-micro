package com.foodcourt.user_micro.domain.api;

import com.foodcourt.user_micro.domain.model.Auth;

public interface IAuthServicePort {

    String login(Auth auth);
}

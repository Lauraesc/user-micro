package com.foodcourt.user_micro.domain.api;

import com.foodcourt.user_micro.domain.model.User;

public interface IUserServicePort {

    User saveUserAdmin(User user);

    Boolean getUserRole(Long id, String role);
}

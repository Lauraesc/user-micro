package com.foodcourt.user_micro.domain.spi;

import com.foodcourt.user_micro.domain.model.Role;

public interface IRolePersistencePort {

    Role findRoleByName(String role);
}

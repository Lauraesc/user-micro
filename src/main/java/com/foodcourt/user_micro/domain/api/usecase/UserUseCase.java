package com.foodcourt.user_micro.domain.api.usecase;

import com.foodcourt.user_micro.domain.api.IUserServicePort;
import com.foodcourt.user_micro.domain.exception.UserNullException;
import com.foodcourt.user_micro.domain.model.User;
import com.foodcourt.user_micro.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    @Override
    public User saveUser(User user) {
        validateUser(user);
        userPersistencePort.saveUser(user);
        return null;
    }

    private void validateUser(User user){
        if (user == null) {
            throw new UserNullException("El usuario no puede ser nulo");
        }
        if(user.getDni().isEmpty()){
            throw new UserNullException("El DNI no puede ser nulo");

        }
        if(isUserValid(user)){
            throw new UserNullException("El usuario no es valido");
        }

    }

    private boolean isUserValid(User user){
        return true;
    }
}

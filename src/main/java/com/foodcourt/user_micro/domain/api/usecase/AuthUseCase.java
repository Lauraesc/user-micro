package com.foodcourt.user_micro.domain.api.usecase;

import com.foodcourt.user_micro.domain.api.IAuthServicePort;
import com.foodcourt.user_micro.domain.model.Auth;
import com.foodcourt.user_micro.domain.model.User;
import com.foodcourt.user_micro.domain.spi.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthUseCase implements IAuthServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncodePersistencePort passwordEncodePersistencePort;
    private final IAuthPersistencePort authPersistencePort;
    private final ITokenPersistencePort tokenPersistencePort;

    @Override
    public String login(Auth auth) {
        User user = userPersistencePort.existsUserByEmail(auth.getEmail()).get(); //el .get es para traer el usuario de ese optional
        if(user == null){
            throw new RuntimeException("Usuario no encontrado");
            //crear mi propia excepción
        }
        if(!passwordEncodePersistencePort.checkPassword(auth.getPassword(), user.getPassword())){
            throw new RuntimeException("Contraseña incorrecta");
            //crear mi propia excepción
        }
        authPersistencePort.login(auth);

        return tokenPersistencePort.getToken(user);

    }

}

package com.foodcourt.user_micro.adapters.driven.adapter;

import com.foodcourt.user_micro.domain.model.Auth;
import com.foodcourt.user_micro.domain.spi.IAuthPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RequiredArgsConstructor
public class AuthAdapter implements IAuthPersistencePort {
    private final AuthenticationManager authenticationManager; //es el que permite acceder a la autenticación y ella recibe credenciales, detalles, principal, etc.
    @Override
    public void login(Auth auth) { //con esto el user queda autenticado, dentro del contexto de autenticación
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
    }
}

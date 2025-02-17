package com.foodcourt.user_micro.adapters.driven.adapter;

import com.foodcourt.user_micro.domain.model.User;
import com.foodcourt.user_micro.domain.spi.IPasswordEncodePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class PasswordEncodeAdapter implements IPasswordEncodePersistencePort {
    private final PasswordEncoder passwordEncoder;
    @Override
    public void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); //aqui se pide encriptar el pass que llega del user

    }

    @Override
    public boolean checkPassword(String passwordLogin, String passwordUser) {
        return passwordEncoder.matches(passwordLogin, passwordUser);
    }
}

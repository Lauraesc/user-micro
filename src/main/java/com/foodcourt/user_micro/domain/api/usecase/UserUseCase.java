package com.foodcourt.user_micro.domain.api.usecase;

import com.foodcourt.user_micro.domain.api.IUserServicePort;
import com.foodcourt.user_micro.domain.exception.EmailAlreadyExistsException;
import com.foodcourt.user_micro.domain.exception.RoleNoDataFoundException;
import com.foodcourt.user_micro.domain.exception.UserNoDataFoundException;
import com.foodcourt.user_micro.domain.exception.UserNotAdultException;
import com.foodcourt.user_micro.domain.model.Role;
import com.foodcourt.user_micro.domain.model.User;
import com.foodcourt.user_micro.domain.spi.IRolePersistencePort;
import com.foodcourt.user_micro.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;


@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    @Override
    public User saveUserAdmin(User user) {
        validateUser(user);
        user.setRole(getRole("ADMIN"));
        User userSaved = userPersistencePort.saveUser(user);
        return userSaved;
    }

    public User saveUserOwner(User user) {
        validateUser(user);
        user.setRole(getRole("OWNER"));
        User userSaved = userPersistencePort.saveUser(user);
        return userSaved;

    }

    private Role getRole(String role){

        Role roleSaved = rolePersistencePort.findRoleByName(role);
        if(roleSaved == null) {
            throw new RoleNoDataFoundException("El rol no puede ser nulo");
        }
        return roleSaved;
    }

    private void validateUser(User user){
        if (user == null) {
            throw new UserNoDataFoundException("El usuario no puede ser nulo");
        }
        if(user.getDni().isEmpty()){
            throw new UserNoDataFoundException("El DNI no puede ser nulo");
        }
        if(!isUserValid(user)){
            throw new UserNoDataFoundException("El usuario no es valido");
        }
        if(userPersistencePort.existsUserByEmail(user.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Ya hay un usuario con ese email");
        }
        if (!isAdult(user.getBirthDate())) {
            throw new UserNotAdultException("El usuario debe ser mayor de edad");
        }

    }

    private boolean isAdult(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }

    private boolean isUserValid(User user){
        return true;
    }
}

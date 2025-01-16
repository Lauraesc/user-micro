package com.foodcourt.user_micro.configuration.bean;

import com.foodcourt.user_micro.adapters.driven.adapter.RoleAdapter;
import com.foodcourt.user_micro.adapters.driven.adapter.UserAdapter;
import com.foodcourt.user_micro.adapters.driven.mapper.IRoleMapperEntity;
import com.foodcourt.user_micro.adapters.driven.mapper.IUserMapperEntity;
import com.foodcourt.user_micro.adapters.driven.repository.IRoleRepository;
import com.foodcourt.user_micro.adapters.driven.repository.IUserRepository;
import com.foodcourt.user_micro.domain.api.IUserServicePort;
import com.foodcourt.user_micro.domain.api.usecase.UserUseCase;
import com.foodcourt.user_micro.domain.spi.IRolePersistencePort;
import com.foodcourt.user_micro.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration //esta anotacion indica que esta es la capa de config
public class BeanConfiguration {

    private final IUserMapperEntity userMapperEntity;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IRoleMapperEntity roleMapperEntity;

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort,
                                            IRolePersistencePort rolePersistencePort) {
        return new UserUseCase(userPersistencePort, rolePersistencePort);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleAdapter(roleRepository, roleMapperEntity);
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userMapperEntity);
    }
}

package com.foodcourt.user_micro.configuration.bean;

import com.foodcourt.user_micro.adapters.driven.adapter.AuthAdapter;
import com.foodcourt.user_micro.adapters.driven.adapter.PasswordEncodeAdapter;
import com.foodcourt.user_micro.adapters.driven.adapter.RoleAdapter;
import com.foodcourt.user_micro.adapters.driven.adapter.UserAdapter;
import com.foodcourt.user_micro.adapters.driven.mapper.IRoleMapperEntity;
import com.foodcourt.user_micro.adapters.driven.mapper.IUserMapperEntity;
import com.foodcourt.user_micro.adapters.driven.repository.IRoleRepository;
import com.foodcourt.user_micro.adapters.driven.repository.IUserRepository;
import com.foodcourt.user_micro.configuration.jwt.JwtService;
import com.foodcourt.user_micro.domain.api.IAuthServicePort;
import com.foodcourt.user_micro.domain.api.IUserServicePort;
import com.foodcourt.user_micro.domain.api.usecase.AuthUseCase;
import com.foodcourt.user_micro.domain.api.usecase.UserUseCase;
import com.foodcourt.user_micro.domain.spi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        return new UserUseCase(userPersistencePort, rolePersistencePort, passwordEncodePersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {

        return new RoleAdapter(roleRepository, roleMapperEntity);
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {

        return new UserAdapter(userRepository, userMapperEntity);
    }

    @Bean
    public IPasswordEncodePersistencePort passwordEncodePersistencePort() {
        return new PasswordEncodeAdapter(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public IAuthPersistencePort authPersistencePort(AuthenticationManager authenticationManager) {
        return new AuthAdapter(authenticationManager);
    }


    @Bean
    public ITokenPersistencePort tokenPersistencePort() {
        return new JwtService();

    }


    @Bean
    public IAuthServicePort authServicePort(IAuthPersistencePort authPersistencePort) {
        return new AuthUseCase(userPersistencePort(), passwordEncodePersistencePort(), authPersistencePort, tokenPersistencePort());
    }

    @Bean
    public UserDetailsService userDetailsService(){ //cambiar runtimeexception por una excepcion personalizada
        return email -> (UserDetails) userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("email not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); // consultar
        authenticationProvider.setUserDetailsService(userDetailsService()); //autenticar un usuario que no se sabe de donde viene
        authenticationProvider.setPasswordEncoder(passwordEncoder());  //autenticar una contrasena que viene encriptada, pero que no se sabe de donde viene
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}

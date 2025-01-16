package com.foodcourt.user_micro.adapters.driven.repository;

import com.foodcourt.user_micro.adapters.driven.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);}

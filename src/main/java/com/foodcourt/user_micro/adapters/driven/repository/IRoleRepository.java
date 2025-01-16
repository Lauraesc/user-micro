package com.foodcourt.user_micro.adapters.driven.repository;

import com.foodcourt.user_micro.adapters.driven.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}

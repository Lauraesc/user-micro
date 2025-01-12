package com.foodcourt.user_micro.adapters.driven.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@Table(name = "roles")
@NoArgsConstructor
@Data
public class RoleEntity {

    private Long id;
    private String name;
}

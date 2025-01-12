package com.foodcourt.user_micro.adapters.driven.entity;


import com.foodcourt.user_micro.domain.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data //pq aparte de que es una tabla tambien sirve como objeto
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firsName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phone;
    private String address;
    private LocalDate birthDate;
    private String dni;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleEntity;
}

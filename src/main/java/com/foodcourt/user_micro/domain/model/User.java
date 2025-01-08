package com.foodcourt.user_micro.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String name;
    private String surname;
    private String dni;
    private Long cellphoneNumber;
}

package com.foodcourt.user_micro.adapters.driving.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseSave {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}

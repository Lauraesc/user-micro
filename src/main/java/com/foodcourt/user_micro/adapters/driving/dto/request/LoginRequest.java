package com.foodcourt.user_micro.adapters.driving.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "el email es obligatorio")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "La contraseña el obligatoria")
    @Pattern(regexp = "^[a-z0-9]+$", message = "La contraseña solo debe contener letras minúsculas y números")
    private String password;
}

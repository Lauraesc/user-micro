package com.foodcourt.user_micro.adapters.driving.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 50, message = "First name must be at least 2 characters long")
    private String firsName;
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    private String password;
    private String phone;
    private String address;
    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
    private String role;
    @NotBlank(message = "DNI is required")
    @Pattern(regexp = "^[0-9]{8}[A-Z]$", message = "DNI format invalid")
    private String dni;
}

package com.foodcourt.user_micro.adapters.driving.controller;


import com.foodcourt.user_micro.adapters.driving.dto.request.LoginRequest;
import com.foodcourt.user_micro.adapters.driving.mapper.IAuthMapperDto;
import com.foodcourt.user_micro.domain.api.IAuthServicePort;
import com.foodcourt.user_micro.domain.model.Auth;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthServicePort authServicePort;
    private final IAuthMapperDto authMapperDto;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest){
        Auth auth = authMapperDto.toAuth(loginRequest);
        return ResponseEntity.ok(authServicePort.login(auth));
    }
}

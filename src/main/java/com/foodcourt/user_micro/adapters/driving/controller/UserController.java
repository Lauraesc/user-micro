package com.foodcourt.user_micro.adapters.driving.controller;


import com.foodcourt.user_micro.adapters.driving.dto.request.UserRequest;
import com.foodcourt.user_micro.adapters.driving.dto.response.UserResponseSave;
import com.foodcourt.user_micro.adapters.driving.mapper.IUserMapperDto;
import com.foodcourt.user_micro.domain.api.IUserServicePort;
import com.foodcourt.user_micro.domain.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final IUserServicePort userServicePort;
    private final IUserMapperDto userMapperDto;

    @PostMapping("/")
    public ResponseEntity<UserResponseSave> saveUserAdmin(@Valid @RequestBody UserRequest userRequest) {
        User user = userServicePort.saveUserAdmin(userMapperDto.userRequestToUser(userRequest));
        return ResponseEntity.ok(userMapperDto.userToUserResponseSave(user));
    }

    @GetMapping("/user-role")
    public ResponseEntity<Boolean> userRole(@RequestParam Long id,@RequestParam String role){
        return ResponseEntity.ok(userServicePort.getUserRole(id,role));
    }
}

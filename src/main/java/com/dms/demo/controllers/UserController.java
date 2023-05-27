package com.dms.demo.controllers;

import com.dms.demo.models.dto.UserDTO;
import com.dms.demo.models.dto.auth.RegisterRequestDTO;
import com.dms.demo.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<RegisterRequestDTO> registerNewUser(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(registerRequestDTO));
    }

    @PutMapping("users/update/{id}")
    public ResponseEntity<UserDTO> updateUserProfile(@PathVariable String id, @RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUserProfile(id, userDTO));
    }
}
package com.dms.demo.controllers;

import com.dms.demo.models.dto.UserDTO;
import com.dms.demo.models.dto.auth.user.UserLoginRequestDTO;
import com.dms.demo.models.dto.auth.user.UserRegisterRequestDTO;
import com.dms.demo.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<UserRegisterRequestDTO> registerNewUser(@RequestBody @Valid UserRegisterRequestDTO userRegisterRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRegisterRequestDTO));
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserLoginRequestDTO> userLogin(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.userLogin(userLoginRequestDTO));
    }

    @PutMapping("users/update/{id}")
    public ResponseEntity<UserDTO> updateUserProfile(@PathVariable String id, @RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUserProfile(id, userDTO));
    }

    @DeleteMapping("users/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
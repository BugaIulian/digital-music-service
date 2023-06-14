package com.dms.demo.controllers;

import com.dms.demo.models.dto.ChangePasswordRequestDTO;
import com.dms.demo.models.dto.UserDTO;
import com.dms.demo.models.dto.auth.user.UserLoginRequestDTO;
import com.dms.demo.models.dto.auth.user.UserRegisterRequestDTO;
import com.dms.demo.services.user.UserService;
import com.dms.demo.util.enums.Gender;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterRequestDTO> registerNewUser(@RequestBody @Valid UserRegisterRequestDTO userRegisterRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.userRegisterRequest(userRegisterRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginRequestDTO> userLogin(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.userLoginRequest(userLoginRequestDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUserProfile(@PathVariable String id, @RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUserProfile(id, userDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(value = "firstname", required = false) String firstName,
                                                  @RequestParam(value = "gender", required = false) Gender gender,
                                                  @RequestParam(value = "city", required = false) String city) {
        return ResponseEntity.ok(userService.getUsers(firstName, city, gender));
    }

    @PostMapping("/request-password-change/{id}")
    public ResponseEntity<String> requestPasswordChange(@PathVariable String id) {
        userService.requestPasswordChange(id);
        return ResponseEntity.ok("Password change requested. A token has been sent to your phone, the token will be available 1 minute.");
    }

    @PostMapping("/confirm-password-change/{id}")
    public ResponseEntity<String> changePassword(@PathVariable String id, @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        userService.confirmPasswordChange(id, changePasswordRequestDTO);
        return ResponseEntity.ok("Password changed successfully!");
    }
}
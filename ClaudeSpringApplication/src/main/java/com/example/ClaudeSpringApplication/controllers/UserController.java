package com.example.ClaudeSpringApplication.controllers;

import com.example.ClaudeSpringApplication.dtos.CreateUserDTO;
import com.example.ClaudeSpringApplication.dtos.UpdatePasswordDTO;
import com.example.ClaudeSpringApplication.entities.User;
import com.example.ClaudeSpringApplication.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDTO user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordDTO passwordDto) {
        return ResponseEntity.ok(userService.updatePassword(id, passwordDto.newPassword()));
    }

    @PatchMapping("/{id}/toggle-lock")
    public ResponseEntity<User> toggleLock(@PathVariable Long id) {
        return ResponseEntity.ok(userService.toggleLockStatus(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
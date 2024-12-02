package com.akechsalim.community_service_management_system.controller;

import com.akechsalim.community_service_management_system.model.Role;
import com.akechsalim.community_service_management_system.model.User;
import com.akechsalim.community_service_management_system.security.JwtUtils;
import com.akechsalim.community_service_management_system.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {

        // Validate that username and password are provided
        if (registerRequest.getUsername() == null || registerRequest.getPassword() == null || registerRequest.getRole() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username, password, and role are required.");
        }

        // Check if user already exists
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken.");
        }

        // Create a new user
        User user = userService.createUser(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getRole());

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Validate user using the UserService
        User user = userService.validateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (user != null) {
            // Generate JWT token based on the validated user's role
            String token = jwtUtils.generateToken(user.getUsername(), user.getRole());
            // Return token and role in response
            return ResponseEntity.ok(new AuthResponse(token, user.getRole().name()));
        }

        // If user is invalid, return 401 Unauthorized
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}


package com.akechsalim.community_service_management_system.controller;

import com.akechsalim.community_service_management_system.model.Role;
import com.akechsalim.community_service_management_system.model.User;
import com.akechsalim.community_service_management_system.security.JwtUtils;
import com.akechsalim.community_service_management_system.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
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

class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {
    }
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class AuthResponse {
    private String token;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

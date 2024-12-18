package com.akechsalim.community_service_management_system.controller;

import com.akechsalim.community_service_management_system.dto.AuthResponseDTO;
import com.akechsalim.community_service_management_system.dto.LoginRequestDTO;
import com.akechsalim.community_service_management_system.dto.UserRegisterDTO;
import com.akechsalim.community_service_management_system.model.User;
import com.akechsalim.community_service_management_system.security.JwtUtils;
import com.akechsalim.community_service_management_system.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        userService.createUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully: " + userRegisterDTO.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        User user = userService.validateUser(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        String token = jwtUtils.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(new AuthResponseDTO(token, user.getRole().name()));
    }
}
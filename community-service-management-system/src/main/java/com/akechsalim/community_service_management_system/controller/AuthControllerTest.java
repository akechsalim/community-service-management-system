package com.akechsalim.community_service_management_system.controller;

import com.akechsalim.community_service_management_system.model.Role;
import com.akechsalim.community_service_management_system.model.User;
import com.akechsalim.community_service_management_system.security.JwtUtils;
import com.akechsalim.community_service_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin( "*") // Allow requests from your front-end
public class AuthControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthControllerTest.class);

    private final JwtUtils jwtUtils;

    private String token;
    private String role;




    public AuthControllerTest(JwtUtils jwtUtils, @Value("${jwt.token}") String token,@Value("${jwt.role}") String role) {
        this.jwtUtils = jwtUtils;
        this.token = token;
        this.role = role;
    }
    // Getter for token
    public String getToken() { return token; }

    // Setter for token
    public void setToken(String token) { this.token = token; }

    // Getter for role
    public String getRole()
    { return role; }

    // Setter for role
    public void setRole(String role) { this.role = role; }


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody LoginRequestTest loginRequestTest) {
        logger.info("Login request received: {}", loginRequestTest);

        // Validate the request body
        if (loginRequestTest.getUsername() == null || loginRequestTest.getUsername().isEmpty() ||
                loginRequestTest.getPassword() == null || loginRequestTest.getPassword().isEmpty()) {
            logger.warn("Invalid request: Username or password is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password must not be empty");
        }

        // Mock user validation (replace with real authentication logic)
        if ("admin".equals(loginRequestTest.getUsername()) && "password123".equals(loginRequestTest.getPassword())) {
            logger.info("Admin user authenticated");
            String token = jwtUtils.generateToken(loginRequestTest.getUsername(), Role.ADMIN);
            return ResponseEntity.ok(new AuthResponseTest(token, "ADMIN"));
        } else if ("volunteer".equals(loginRequestTest.getUsername()) && "password123".equals(loginRequestTest.getPassword())) {
            logger.info("Volunteer user authenticated");
            String token = jwtUtils.generateToken(loginRequestTest.getUsername(), Role.VOLUNTEER);
            return ResponseEntity.ok(new AuthResponseTest(token, "VOLUNTEER"));
        }

        logger.warn("Invalid credentials provided");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }




}

class LoginRequestTest {
    private String username;
    private String password;

    public LoginRequestTest() {
    }
    public LoginRequestTest(String username, String password) {
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

class AuthResponseTest {
    private String token;
    private String role;

    public AuthResponseTest() {
    }

    public AuthResponseTest(String token, String role) {
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

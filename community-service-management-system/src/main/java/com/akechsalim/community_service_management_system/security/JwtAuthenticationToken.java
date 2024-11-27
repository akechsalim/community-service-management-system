package com.akechsalim.community_service_management_system.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String username;
    private final String role;

    // Constructor that initializes the token with the username and role (authorities)
    public JwtAuthenticationToken(String username, String role) {
        super(Collections.singletonList(new SimpleGrantedAuthority(role))); // Set the role as an authority
        this.username = username;
        this.role = role;
        setAuthenticated(true); // Mark the token as authenticated since we've validated it
    }

    // Get the principal (username)
    public String getUsername() {
        return username;
    }

    // Get the authorities (roles)
    @Override
    public Object getCredentials() {
        return null; // Credentials are usually used for password, but here we use JWT, so it's null
    }

    @Override
    public Object getPrincipal() {
        return username; // Return the username as the principal
    }
    public String getRole() {
        return this.role;
    }
    @Override
    public String toString() {
        return "JwtAuthenticationToken{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

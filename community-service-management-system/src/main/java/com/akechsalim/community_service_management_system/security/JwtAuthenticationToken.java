package com.akechsalim.community_service_management_system.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    // Get the principal (username)
    private final String username;
    private final String role;

    // Constructor that initializes the token with the username and role (authorities)
    public JwtAuthenticationToken(String username, String role) {
        super(Collections.singletonList(new SimpleGrantedAuthority(role))); // Set the role as an authority
        this.username = username;
        this.role = role;
        setAuthenticated(true); // Mark the token as authenticated since we've validated it
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

    @Override
    public String toString() {
        return "JwtAuthenticationToken{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

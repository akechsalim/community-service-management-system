package com.akechsalim.community_service_management_system.security;

import com.akechsalim.community_service_management_system.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private final String secretKey = "your_secret_key"; // Keep your secret key

    // Generate JWT token
    public String generateToken(String username, Role role) {
        // 24 hours in milliseconds
        long expiration = 86400000;
        return Jwts.builder()
                .claim("username", username) // Add username as a claim
                .claim("role", role.toString()) // Add role as a claim
                .setIssuedAt(new Date()) // Set issued time
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Set expiration time
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()) // Sign with the secret key
                .compact();
    }

    // Extract username from JWT token
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).get("username", String.class); // Retrieve the "username" claim
    }

    // Extract role from JWT token
    public String getRoleFromToken(String token) {
        return getClaimsFromToken(token).get("role", String.class); // Retrieve the "role" claim
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            getClaimsFromToken(token); // Parses and validates the token
            return true;
        } catch (Exception e) {
            return false; // Invalid token
        }
    }

    // Helper method to extract claims from a token
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}

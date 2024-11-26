package com.akechsalim.community_service_management_system.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private final String secretKey = "your_secret_key"; // Secret key for signing JWTs
    private long expiration = 86400000; // 24 hours in milliseconds

    // Generate JWT token
    public String generateToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username).build();
        claims.put("role", role);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // Extract username from JWT token
    public String getUsernameFromToken(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(secretKey) // Set the secret key for signature verification
                .build();

        return parser.parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Extract role from JWT token
    public String getRoleFromToken(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(secretKey)
                .build();

        return (String) parser.parseClaimsJws(token)
                .getBody()
                .get("role");
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build();
            parser.parseClaimsJws(token); // If it throws an exception, token is invalid
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

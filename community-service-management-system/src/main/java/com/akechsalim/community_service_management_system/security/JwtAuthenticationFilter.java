package com.akechsalim.community_service_management_system.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;


import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl); // Filters requests that have /api/**
        this.jwtUtils = jwtUtils;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String token = getTokenFromRequest(request);  // Extract token from the request header

        if (isTokenValid(token)) {
            // Extract username and role from token
            String username = jwtUtils.getUsernameFromToken(token);
            String role = jwtUtils.getRoleFromToken(token);

            JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(username, role);
            return getAuthenticationManager().authenticate(authenticationToken);
        }

        return null;  // Return null if the token is not valid
    }

    // Helper method to check if the token is valid
    private boolean isTokenValid(String token) {
        return token != null && jwtUtils.validateToken(token);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

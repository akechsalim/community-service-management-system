package com.akechsalim.community_service_management_system.security;

import com.akechsalim.community_service_management_system.model.Role;
import com.akechsalim.community_service_management_system.service.UserService;
import org.springframework.boot.CommandLineRunner;

public class DataLoader implements CommandLineRunner {
    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        if (!userService.existsByUsername("admin")) {
            userService.createUser("admin", "password123", Role.ADMIN);
        }
        if (!userService.existsByUsername("volunteer")) {
            userService.createUser("volunteer", "password123", Role.VOLUNTEER);
        }
    }
}

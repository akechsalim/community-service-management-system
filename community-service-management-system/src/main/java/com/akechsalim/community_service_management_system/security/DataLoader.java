package com.akechsalim.community_service_management_system.security;

import com.akechsalim.community_service_management_system.dto.UserRegisterDTO;
import com.akechsalim.community_service_management_system.model.Role;
import com.akechsalim.community_service_management_system.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        addDefaultUsers();
    }

    private void addDefaultUsers() {
        if (!userService.existsByUsername("admin")) {
            userService.createUser(new UserRegisterDTO("admin", "securePassword1!", Role.ADMIN));
        }
        if (!userService.existsByUsername("volunteer")) {
            userService.createUser(new UserRegisterDTO("volunteer", "securePassword2!", Role.VOLUNTEER));
        }
    }
}
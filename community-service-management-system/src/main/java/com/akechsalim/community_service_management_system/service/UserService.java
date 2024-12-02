package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.model.Role;
import com.akechsalim.community_service_management_system.model.User;
import com.akechsalim.community_service_management_system.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository; // Assuming you have a UserRepository to interact with the database

    private final BCryptPasswordEncoder passwordEncoder; // To encode/decode passwords

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User createUser(String username, String password, Role role) {
        String encodedPassword = passwordEncoder.encode(password); // Encrypt password
        User newUser = new User(username, encodedPassword, role); // Create user
        return userRepository.save(newUser); // Save user to the database
    }

    // Method to validate the user and return User object
    public User validateUser(String username, String password) {
        // Fetch user by username from the repository
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check if the password matches
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null; // Invalid password
    }

}


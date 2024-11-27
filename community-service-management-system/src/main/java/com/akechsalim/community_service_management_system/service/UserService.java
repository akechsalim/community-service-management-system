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

    // Method to register a new user (for example)
    public User registerUser(String username, String password, Role role) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already taken");
        }

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, encodedPassword, role);

        return userRepository.save(newUser); // Save the user to the database
    }

}


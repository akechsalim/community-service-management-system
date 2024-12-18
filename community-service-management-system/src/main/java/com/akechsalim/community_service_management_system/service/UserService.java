package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.dto.UserDTO;
import com.akechsalim.community_service_management_system.dto.UserRegisterDTO;
import com.akechsalim.community_service_management_system.exceptions.UserAlreadyExistsException;
import com.akechsalim.community_service_management_system.model.User;
import com.akechsalim.community_service_management_system.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDTO createUser(UserRegisterDTO dto) {
        if (existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("User with username " + dto.getUsername() + " already exists.");
        }
        User user = new User(dto.getUsername(), passwordEncoder.encode(dto.getPassword()), dto.getRole());
        return convertToDTO(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Transactional(readOnly = true)
    public UserDTO findByUsername(String username) {
        return convertToDTO(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username)));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        user.setRole(dto.getRole());
        user.setUsername(dto.getUsername()); // Assuming username can be updated
        return convertToDTO(userRepository.save(user));
    }

    // Method to validate user credentials
    @Transactional(readOnly = true)
    public User validateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return user;
    }

    // Helper method to convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRole());
    }
}
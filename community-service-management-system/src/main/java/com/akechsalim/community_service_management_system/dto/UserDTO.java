package com.akechsalim.community_service_management_system.dto;

import com.akechsalim.community_service_management_system.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private Role role;
}
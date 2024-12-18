package com.akechsalim.community_service_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerContributionDTO {
    private Long id;

    @NotNull(message = "Volunteer ID is required")
    private Long volunteerId;

    @NotBlank(message = "Volunteer name is required")
    private String volunteerName;

    @NotNull(message = "Event ID is required")
    private Long eventId;

    @Positive(message = "Hours contributed must be positive")
    private Double hours;

    private String description;

    private LocalDateTime createdAt;
}
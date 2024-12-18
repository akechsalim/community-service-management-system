package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.dto.VolunteerContributionDTO;
import com.akechsalim.community_service_management_system.exceptions.ResourceNotFoundException;
import com.akechsalim.community_service_management_system.model.VolunteerContribution;
import com.akechsalim.community_service_management_system.repository.VolunteerContributionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerContributionService {

    private final VolunteerContributionRepository repository;

    public VolunteerContributionService(VolunteerContributionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<VolunteerContributionDTO> getAllContributions() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public VolunteerContributionDTO addContribution(VolunteerContributionDTO contributionDTO) {
        VolunteerContribution contribution = convertToEntity(contributionDTO);
        contribution = repository.save(contribution);
        return convertToDTO(contribution);
    }

    @Transactional
    public void deleteContribution(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Contribution not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // Helper methods to convert between DTO and Entity

    private VolunteerContributionDTO convertToDTO(VolunteerContribution contribution) {
        return new VolunteerContributionDTO(
                contribution.getId(),
                contribution.getVolunteerId(),
                contribution.getVolunteerName(),
                contribution.getEventId(),
                contribution.getHours(),
                contribution.getDescription(),
                contribution.getCreatedAt()
        );
    }
    private VolunteerContribution convertToEntity(VolunteerContributionDTO dto) {
        VolunteerContribution contribution = new VolunteerContribution();
        contribution.setVolunteerId(dto.getVolunteerId());
        contribution.setVolunteerName(dto.getVolunteerName());
        contribution.setEventId(dto.getEventId());
        contribution.setHours(dto.getHours());
        contribution.setDescription(dto.getDescription());
        return contribution;
    }

}

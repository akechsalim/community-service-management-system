package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.model.VolunteerContribution;
import com.akechsalim.community_service_management_system.repository.VolunteerContributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerContributionService {

    private final VolunteerContributionRepository repository;

    public VolunteerContributionService(VolunteerContributionRepository repository) {
        this.repository = repository;
    }

    public List<VolunteerContribution> getAllContributions() {
        return repository.findAll();
    }

    public VolunteerContribution addContribution(VolunteerContribution contribution) {
        return repository.save(contribution);
    }

    public void deleteContribution(Long id) {
        repository.deleteById(id);
    }
}

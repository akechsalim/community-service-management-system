package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.model.Volunteer;
import com.akechsalim.community_service_management_system.repository.VolunteerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Transactional
    public Volunteer addVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }
    public void deleteVolunteer(Long id) {
        if (!volunteerRepository.existsById(id)) {
            throw new EntityNotFoundException("Volunteer with ID " + id + " not found");
        }
        volunteerRepository.deleteById(id);
    }

}

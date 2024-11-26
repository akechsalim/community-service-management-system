package com.akechsalim.community_service_management_system.controller;

import com.akechsalim.community_service_management_system.model.VolunteerContribution;
import com.akechsalim.community_service_management_system.service.VolunteerContributionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/contributions")
public class VolunteerContributionController {

    private final VolunteerContributionService service;

    public VolunteerContributionController(VolunteerContributionService service) {
        this.service = service;
    }

    @GetMapping
    public List<VolunteerContribution> getAllContributions() {
        return service.getAllContributions();
    }

    @PostMapping
    public VolunteerContribution addContribution(@RequestBody VolunteerContribution contribution) {
        return service.addContribution(contribution);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContribution(@PathVariable Long id) {
        service.deleteContribution(id);
        return ResponseEntity.noContent().build();
    }
}

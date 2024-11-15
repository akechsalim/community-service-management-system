package com.akechsalim.community_service_management_system.controller;

import com.akechsalim.community_service_management_system.model.Volunteer;
import com.akechsalim.community_service_management_system.service.VolunteerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @PostMapping
    public Volunteer addVolunteer(@RequestBody Volunteer volunteer) {
        return volunteerService.addVolunteer(volunteer);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok("Volunteer deleted successfully");
    }
}

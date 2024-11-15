package com.akechsalim.community_service_management_system.model;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer; // Volunteer assigned to the task

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    // Constructors
    public Task() {
    }

    public Task(String name, String description, Volunteer assignedTo, boolean completed) {
        this.name = name;
        this.description = description;
        this.volunteer = assignedTo;
        this.completed = completed;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer assignedTo) {
        this.volunteer = assignedTo;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

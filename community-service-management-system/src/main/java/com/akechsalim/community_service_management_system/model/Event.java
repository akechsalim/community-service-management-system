package com.akechsalim.community_service_management_system.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate date;
    private String location;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "events")
    private Set<Volunteer> volunteers;

    public Event() {
    }

    public Event(String name, String description, LocalDate date, String location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

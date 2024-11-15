package com.akechsalim.community_service_management_system.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private String contactInfo;

    @ManyToMany
    @JoinTable(
            name = "volunteer_event",
            joinColumns = @JoinColumn(name = "volunteer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events;

    @OneToMany(mappedBy = "volunteer")
    private Set<Task> tasks;

    public Volunteer() {
    }

    public Volunteer(String name, String role, String contactInfo) {
        this.name = name;
        this.role = role;
        this.contactInfo = contactInfo;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}

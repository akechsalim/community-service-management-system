package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.exceptions.ResourceNotFoundException;
import com.akechsalim.community_service_management_system.model.Event;
import com.akechsalim.community_service_management_system.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setName(updatedEvent.getName());
                    event.setDescription(updatedEvent.getDescription());
                    event.setLocation(updatedEvent.getLocation());
                    event.setStartTime(updatedEvent.getStartTime());
                    event.setEndTime(updatedEvent.getEndTime());
                    return eventRepository.save(event);
                }).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }


}

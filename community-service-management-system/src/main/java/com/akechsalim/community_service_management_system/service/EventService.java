package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.dto.EventDTO;
import com.akechsalim.community_service_management_system.exceptions.ResourceNotFoundException;
import com.akechsalim.community_service_management_system.model.Event;
import com.akechsalim.community_service_management_system.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional(readOnly = true)
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = convertToEntity(eventDTO);
        event = eventRepository.save(event);
        return convertToDTO(event);
    }

    @Transactional(readOnly = true)
    public EventDTO getEventById(Long id) {
        return eventRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    @Transactional
    public EventDTO updateEvent(Long id, EventDTO updatedEventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        event.setName(updatedEventDTO.getName());
        event.setDescription(updatedEventDTO.getDescription());
        event.setLocation(updatedEventDTO.getLocation());
        event.setStartTime(updatedEventDTO.getStartTime());
        event.setEndTime(updatedEventDTO.getEndTime());
        event = eventRepository.save(event);
        return convertToDTO(event);
    }
    @Transactional
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }

    // Helper methods to convert between DTO and Entity

    private EventDTO convertToDTO(Event event) {
        return new EventDTO(event.getId(), event.getName(), event.getDescription(), event.getLocation(), event.getStartTime(), event.getEndTime());
    }

    private Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setStartTime(eventDTO.getStartTime());
        event.setEndTime(eventDTO.getEndTime());
        return event;
    }


}

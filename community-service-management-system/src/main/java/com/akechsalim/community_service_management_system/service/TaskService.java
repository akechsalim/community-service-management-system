package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.model.Event;
import com.akechsalim.community_service_management_system.model.Task;
import com.akechsalim.community_service_management_system.repository.EventRepository;
import com.akechsalim.community_service_management_system.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EventRepository eventRepository;

    public TaskService(TaskRepository taskRepository, EventRepository eventRepository) {
        this.taskRepository = taskRepository;
        this.eventRepository = eventRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }


    @Transactional
    public Task createTask(Task task) {
        // Check if the event exists
        Event event = task.getEvent();
        if (event != null && event.getId() != null) {
            // If event exists, use it
            event = eventRepository.findById(event.getId()).orElse(null);
            if (event == null) {
                // If event doesn't exist, create a new one
                event = new Event();
                event.setName("Default Event Name");  // Set default or required properties
                event = eventRepository.save(event);
            }
        }else {
            // If event is null or doesn't have an ID, create a new one
            event = new Event();
            event.setName("Default Event Name");  // Set default properties
            event = eventRepository.save(event);
        }

        // Set the event to task
        task.setEvent(event);

        // Now save the task with the event (new or existing)
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setName(taskDetails.getName());
            task.setDescription(taskDetails.getDescription());
            task.setVolunteer(taskDetails.getVolunteer()); // Update Volunteer
            task.setEvent(taskDetails.getEvent()); // Update Event
            task.setCompleted(taskDetails.isCompleted());
            return taskRepository.save(task);
        } else {
            // Optional: Handle case where task is not found
            throw new RuntimeException("Task not found with id " + id);
        }
    }
}

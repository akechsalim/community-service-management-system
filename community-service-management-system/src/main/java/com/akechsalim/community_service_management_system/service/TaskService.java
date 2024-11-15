package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.model.Task;
import com.akechsalim.community_service_management_system.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
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
            task.setVolunteer(taskDetails.getVolunteer());
            task.setCompleted(taskDetails.isCompleted());
            return taskRepository.save(task);
        }
        return null;
    }
}

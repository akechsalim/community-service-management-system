package com.akechsalim.community_service_management_system.controller;

import com.akechsalim.community_service_management_system.model.Notification;
import com.akechsalim.community_service_management_system.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api/notifications")
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{recipient}")
    public List<Notification> getUnreadNotifications(@PathVariable String recipient) {
        return notificationService.getUnreadNotifications(recipient);
    }

    @PostMapping
    public Notification sendNotification(@RequestBody Notification notification) {
        return notificationService.sendNotification(notification);
    }

    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {
        return notificationService.markAsRead(id);
    }
}

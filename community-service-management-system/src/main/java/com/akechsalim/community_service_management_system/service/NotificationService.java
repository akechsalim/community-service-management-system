package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.model.Notification;
import com.akechsalim.community_service_management_system.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {


    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getUnreadNotifications(String recipient) {
        return notificationRepository.findByRecipientAndIsRead(recipient, false);
    }

    public Notification sendNotification(Notification notification) {
        notification.setSentTime(LocalDateTime.now());
        notification.setIsRead(false);
        return notificationRepository.save(notification);
    }

    public Notification markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.setIsRead(true);
        return notificationRepository.save(notification);
    }
}

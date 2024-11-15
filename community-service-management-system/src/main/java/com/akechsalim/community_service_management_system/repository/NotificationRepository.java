package com.akechsalim.community_service_management_system.repository;

import com.akechsalim.community_service_management_system.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientAndIsRead(String recipient, boolean isRead);
}


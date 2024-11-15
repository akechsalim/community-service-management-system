package com.akechsalim.community_service_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;
    private String message;
    private LocalDateTime sentTime;
    private boolean isRead;

    public Notification() {
    }

    public Notification(Long id, String recipient, String message, LocalDateTime sentTime, boolean isRead) {
        this.id = id;
        this.recipient = recipient;
        this.message = message;
        this.sentTime = sentTime;
        this.isRead = isRead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }
}

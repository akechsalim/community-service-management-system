package com.akechsalim.community_service_management_system.service;

import com.akechsalim.community_service_management_system.model.Message;
import com.akechsalim.community_service_management_system.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessagesByReceiver(String receiver) {
        return messageRepository.findByReceiver(receiver);
    }

    public Message sendMessage(Message message) {
        message.setSentTime(LocalDateTime.now());
        return messageRepository.save(message);
    }
}

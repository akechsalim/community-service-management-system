package com.akechsalim.community_service_management_system.controller;

import com.akechsalim.community_service_management_system.model.Message;
import com.akechsalim.community_service_management_system.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/messages")
@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{receiver}")
    public List<Message> getMessages(@PathVariable String receiver) {
        return messageService.getMessagesByReceiver(receiver);
    }

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }
}

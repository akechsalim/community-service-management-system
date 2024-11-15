package com.akechsalim.community_service_management_system.repository;

import com.akechsalim.community_service_management_system.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiver(String receiver);
}

package com.akechsalim.community_service_management_system.repository;

import com.akechsalim.community_service_management_system.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}

package com.spirit.stream.dao.repository;

import com.spirit.stream.dao.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByResourceId(String id);
}

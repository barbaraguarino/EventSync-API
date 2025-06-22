package com.uff.eventsync.domain.event.repository;

import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findAllByOrderByDateAscStartTimeAsc();
    List<Event> findByAttendeesContainsOrderByDateAscStartTimeAsc(User attendee);

}
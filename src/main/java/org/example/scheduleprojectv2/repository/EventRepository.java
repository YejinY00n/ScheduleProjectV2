package org.example.scheduleprojectv2.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.example.scheduleprojectv2.entity.Event;
import org.example.scheduleprojectv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
  List<Event> findAllByUserAndModifiedAtBetween(User user, LocalDateTime startDate, LocalDateTime endDate);

  List<Event> findAllByModifiedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

  List<Event> findAllByUser(User user);

  default Event findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Does not exist event id = "+id));
  }
}

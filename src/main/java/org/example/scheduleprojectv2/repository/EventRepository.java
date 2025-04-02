package org.example.scheduleprojectv2.repository;

import org.example.scheduleprojectv2.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  default Event findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Does not exist event id = "+id));
  }
}

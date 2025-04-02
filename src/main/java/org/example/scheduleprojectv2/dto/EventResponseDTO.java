package org.example.scheduleprojectv2.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import org.example.scheduleprojectv2.entity.Event;

@Getter
public class EventResponseDTO {
  private final String task;
  private final String name;
  private final LocalDateTime createdAt;
  private final LocalDateTime modifiedAt;

  public EventResponseDTO(Event event) {
    this.task = event.getTask();
    this.name = event.getUser().getName();
    this.createdAt = event.getCreatedAt();
    this.modifiedAt = event.getModifiedAt();
  }
}

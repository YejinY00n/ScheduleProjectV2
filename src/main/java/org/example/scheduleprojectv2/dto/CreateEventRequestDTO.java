package org.example.scheduleprojectv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateEventRequestDTO {
  private final String task;
  private final String email;
}

package org.example.scheduleprojectv2.dto;

import lombok.Getter;
import org.example.scheduleprojectv2.entity.User;

@Getter
public class UserResponseDTO {
  private final String email;
  private final String name;

  public UserResponseDTO(User user) {
    this.email = user.getEmail();
    this.name = user.getName();
  }
}

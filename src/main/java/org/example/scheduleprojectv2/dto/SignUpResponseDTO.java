package org.example.scheduleprojectv2.dto;

import lombok.Getter;
import org.example.scheduleprojectv2.entity.User;

@Getter
public class SignUpResponseDTO {
  private final Long id;
  private final String email;
  private final String name;

  // Entity --> SignUpResponseDTO
  public SignUpResponseDTO(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.name = user.getName();
  }
}

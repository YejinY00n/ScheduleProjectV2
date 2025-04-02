package org.example.scheduleprojectv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequestDTO {
  private final String email;
  private final String oldPassword;
  private final String newPassword;
  private final String name;
}

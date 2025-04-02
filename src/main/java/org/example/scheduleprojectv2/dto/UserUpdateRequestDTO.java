package org.example.scheduleprojectv2.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequestDTO {
  private String email;
  private String oldPassword;
  private String newPassword;
  private String name;
}

package org.example.scheduleprojectv2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordDTO {
  @NotBlank(message = "비밀번호는 필수값입니다.")
  private final String password;
}

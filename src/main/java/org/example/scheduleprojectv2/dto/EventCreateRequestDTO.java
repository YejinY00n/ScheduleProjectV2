package org.example.scheduleprojectv2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventCreateRequestDTO {

  @Size(min=1, max=50, message="할일은 50글자 이내여야 합니다.")
  @NotBlank(message = "할일 내용은 필수값 입니다.")
  private final String task;

  @Email
  @NotBlank(message = "이메일은 필수값입니다.")
  private final String email;
}

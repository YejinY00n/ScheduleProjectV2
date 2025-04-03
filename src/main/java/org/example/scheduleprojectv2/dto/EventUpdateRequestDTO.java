package org.example.scheduleprojectv2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventUpdateRequestDTO {
  @Size(min=1, max=50, message="할일은 50글자 이내여야 합니다.")
  @NotBlank(message = "할일 내용은 필수값 입니다.")
  private final String task;

  @NotBlank(message = "비밀번호는 필수값입니다.")
  private final String password;
}

package org.example.scheduleprojectv2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordUpdateRequestDTO {
  @NotBlank(message = "기존 비밀번호는 필수값입니다.")
  private final String oldPassword;

  @NotBlank(message = "새로운 비밀번호는 필수값입니다.")
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}",
      message = "비밀번호는 8~20자 영문 대소문자, 숫자를 사용하세요.")
  private final String newPassword;
}

package org.example.scheduleprojectv2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDTO {
  @Email
  @NotBlank(message = "이메일은 필수값입니다.")
  private final String email;

  @NotBlank(message = "비밀번호는 필수값입니다.")
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}",
      message = "비밀번호는 8~20자 영문 대소문자, 숫자를 사용하세요.")
  private final String password;

  @NotBlank(message = "이름은 필수값입니다.")
  @Pattern(regexp = "^(?=.*[a-z가-힣])[a-z가-힣\\s]{2,16}$", message = "이름은 한글, 영소문자의 2~16글자입니다.")
  private final String name;
}

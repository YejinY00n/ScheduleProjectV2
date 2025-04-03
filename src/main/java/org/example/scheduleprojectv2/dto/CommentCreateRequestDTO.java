package org.example.scheduleprojectv2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateRequestDTO {
  @Size(min=1, max=250, message="댓글은 250글자 이내여야 합니다.")
  @NotNull(message = "댓글 내용은 필수값 입니다.")
  private String content;

  @Email
  @NotBlank(message = "이메일은 필수값입니다.")
  private String email;
}

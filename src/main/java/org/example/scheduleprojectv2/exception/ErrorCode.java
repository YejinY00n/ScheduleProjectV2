package org.example.scheduleprojectv2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 자주 사용하는 에러 코드를 enum 으로 정리
@Getter
@AllArgsConstructor
public enum ErrorCode {
  // Common
  // 값 없는 경우, 이메일 비어있는 경우
  INVALID_INPUT_VALUE(400, "Bad Request", "C001", "Invalid Input Value"),

  // User
  USER_NOT_FOUND(404, "Not Found", "U001", "User Not Found"), // 일치하는 이메일이 없다면
  INVALID_PASSWORD(401, "Unauthorized", "U002", "Invalid Password");  // 비밀번호 오류

  // Event


  private final int status;
  private final String error;
  private final String code;
  private final String message;
}

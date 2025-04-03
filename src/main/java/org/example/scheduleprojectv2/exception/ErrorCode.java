package org.example.scheduleprojectv2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 자주 사용하는 에러 코드를 enum 으로 정리
@Getter
@AllArgsConstructor
public enum ErrorCode {
  // Common
  // 값 없는 경우, 이메일 비어있는 경우
  INVALID_INPUT_VALUE(400, "Bad Request", "C001", "Invalid Input Value");

  // User
  // 로그인에서 이메일, 비번 일치하지 않는 경우

  // Event


  private final int status;
  private final String error;
  private final String code;
  private final String message;
}

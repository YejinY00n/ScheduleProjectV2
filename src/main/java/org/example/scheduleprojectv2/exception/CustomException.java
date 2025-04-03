package org.example.scheduleprojectv2.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
  private final ErrorCode errorCode;

  // 에러 코드의 메세지로 생성
  public CustomException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  // 커스텀 메세지로 생성
  public CustomException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
}

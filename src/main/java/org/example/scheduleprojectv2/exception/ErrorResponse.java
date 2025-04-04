package org.example.scheduleprojectv2.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

// 에러 응답 시 사용하는 DTO
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
  @Builder.Default
  private final LocalDateTime timestamp = LocalDateTime.now();
  private final int status;
  private final String error;
  private final String code;
  private final String message;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final List<FieldError> fieldErrors;

  public static ErrorResponse of(ErrorCode errorCode) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .error(errorCode.getError())
        .message(errorCode.getMessage())
        .status(errorCode.getStatus())
        .fieldErrors(new ArrayList<>())
        .build();
  }

  // 에러 코드의 메세지 외 커스텀 에러 메세지 작성
  public static ErrorResponse of(ErrorCode errorCode, String message) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .error(errorCode.getError())
        .message(message)
        .status(errorCode.getStatus())
        .fieldErrors(new ArrayList<>())
        .build();
  }

  public static ErrorResponse of(ErrorCode errorCode, List<FieldError> fieldErrors) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .error(errorCode.getError())
        .message(errorCode.getMessage())
        .status(errorCode.getStatus())
        .fieldErrors(fieldErrors)
        .build();
  }

  // 필드 에러
  @Getter
  @Builder
  public static class FieldError {
    private String field;
    private String value;
    private String reason;

    public static FieldError of(String field, String value, String reason) {
      return FieldError.builder()
          .field(field)
          .value(value)
          .reason(reason)
          .build();
    }
  }
}

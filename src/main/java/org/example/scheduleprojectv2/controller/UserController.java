package org.example.scheduleprojectv2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.common.Const;
import org.example.scheduleprojectv2.dto.LoginRequestDTO;
import org.example.scheduleprojectv2.dto.LoginResponseDTO;
import org.example.scheduleprojectv2.dto.PasswordDTO;
import org.example.scheduleprojectv2.dto.PasswordUpdateRequestDTO;
import org.example.scheduleprojectv2.dto.SignUpRequestDTO;
import org.example.scheduleprojectv2.dto.SignUpResponseDTO;
import org.example.scheduleprojectv2.dto.UserResponseDTO;
import org.example.scheduleprojectv2.dto.UserUpdateRequestDTO;
import org.example.scheduleprojectv2.exception.ErrorCode;
import org.example.scheduleprojectv2.exception.ErrorResponse;
import org.example.scheduleprojectv2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  // 유저 생성
  @PostMapping("/signup")
  public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO requestDto) {
    return new ResponseEntity<>(userService.signUp(requestDto), HttpStatus.CREATED);
  }

  // 유저 조회
  @GetMapping("/{id}")
  public UserResponseDTO findById(@PathVariable Long id) {
    return userService.findById(id);
  }

  // 유저 수정
  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> update(@PathVariable Long id,
      @RequestBody UserUpdateRequestDTO requestDTO) {
    return new ResponseEntity<>(userService.update(id, requestDTO), HttpStatus.OK);
  }

  // 유저 비밀번호 수정
  @PutMapping("/{id}/password")
  public ResponseEntity<Void> updatePassword(@PathVariable Long id,
      @RequestBody PasswordUpdateRequestDTO requestDTO) {
    userService.updatePassword(id, requestDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 유저 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody PasswordDTO passwordDTO) {
    userService.delete(id, passwordDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 유저 로그인
  @PostMapping("/login")
  public String login(@Valid @ModelAttribute LoginRequestDTO requestDTO,
      HttpServletRequest request) {
    LoginResponseDTO responseDTO = userService.login(requestDTO);

    // 로그인 성공 시
    HttpSession session = request.getSession();

    // 회원 정보 조회
    UserResponseDTO loginUser = userService.findById(responseDTO.getId());

    // 세션에 로그인 회원 정보 저장
    // TODO: 세션에 어떤 정보 저장할지
    session.setAttribute(Const.LOGIN_USER, loginUser);

    // TODO: 로그인 성공 시 리다이렉트 (PRG 패턴?)
    return "success";
  }

  // 유저 로그아웃
  @PostMapping("/logout")
  public String logout(HttpServletRequest request) {
    // 로그인 세션 확인
    HttpSession session = request.getSession(false);

    // 로그인인 경우
    if(session != null) {
      session.invalidate();   // 해당 세션 삭제 (로그아웃)
    }
    // TODO: 로그아웃 시 리다이렉트 (PRG 패턴?)
    return "redirect";
  }

  // UserController 내부 @Valid 예외 처리
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException
    (MethodArgumentNotValidException e) {
    List<ErrorResponse.FieldError> fieldErrors = new ArrayList<>();
    BindingResult bindingResult = e.getBindingResult(); // 데이터 바인딩 및 유효성 검사 결과 저장된 객체

    for (FieldError fieldError:bindingResult.getFieldErrors()) {
      ErrorResponse.FieldError error = ErrorResponse.FieldError.of(
        fieldError.getField(),
          fieldError.getRejectedValue().toString(),
          fieldError.getDefaultMessage());
      fieldErrors.add(error);
    }
    ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, fieldErrors);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}

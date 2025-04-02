package org.example.scheduleprojectv2.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.SignUpRequestDTO;
import org.example.scheduleprojectv2.dto.SignUpResponseDTO;
import org.example.scheduleprojectv2.dto.UserResponseDTO;
import org.example.scheduleprojectv2.dto.UserUpdateRequestDTO;
import org.example.scheduleprojectv2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  // TODO: validation 추가
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

  // 유저 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

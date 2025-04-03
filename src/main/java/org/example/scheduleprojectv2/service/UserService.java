package org.example.scheduleprojectv2.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.config.PasswordEncoder;
import org.example.scheduleprojectv2.dto.LoginRequestDTO;
import org.example.scheduleprojectv2.dto.LoginResponseDTO;
import org.example.scheduleprojectv2.dto.PasswordDTO;
import org.example.scheduleprojectv2.dto.PasswordUpdateRequestDTO;
import org.example.scheduleprojectv2.dto.SignUpRequestDTO;
import org.example.scheduleprojectv2.dto.SignUpResponseDTO;
import org.example.scheduleprojectv2.dto.UserResponseDTO;
import org.example.scheduleprojectv2.dto.UserUpdateRequestDTO;
import org.example.scheduleprojectv2.entity.User;
import org.example.scheduleprojectv2.exception.CustomException;
import org.example.scheduleprojectv2.exception.ErrorCode;
import org.example.scheduleprojectv2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // 유저 생성
  public SignUpResponseDTO signUp(SignUpRequestDTO requestDTO) {
    // 이메일이 중복이라면
    if(isExistsEmail(requestDTO.getEmail())) {
      throw new CustomException(ErrorCode.EMAIL_DUPLICATION);
    }
    String encodedPassword = passwordEncoder.encode(requestDTO.getPassword());
    User savedUser = userRepository.save(new User(requestDTO, encodedPassword));
    return new SignUpResponseDTO(savedUser);
  }

  // 유저 조회
  public UserResponseDTO findById(Long id) {
    return new UserResponseDTO(userRepository.findByIdOrElseThrow(id));
  }

  // 유저 수정
  @Transactional
  public UserResponseDTO update(Long id, UserUpdateRequestDTO requestDTO) {
    User user = userRepository.findByIdOrElseThrow(id);
    // 패스워드가 일치하지 않는다면
    if(!isValidPassword(requestDTO.getPassword(), user)) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }
    // 현재 쓰는 이메일이 아니고, 이메일이 중복이라면
    if(!user.getEmail().equals(requestDTO.getEmail()) && isExistsEmail(requestDTO.getEmail())) {
      throw new CustomException(ErrorCode.EMAIL_DUPLICATION);
    }

    user.update(requestDTO);
    return new UserResponseDTO(user);
  }

  // 유저 비밀번호 변경
  @Transactional
  public void updatePassword(Long id, PasswordUpdateRequestDTO requestDTO) {
    User user = userRepository.findByIdOrElseThrow(id);
    // 패스워드가 일치하지 않는다면
    if(!isValidPassword(requestDTO.getOldPassword(), user)) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }
    String encodedPassword = passwordEncoder.encode(requestDTO.getNewPassword());
    user.updatePassword(encodedPassword);
  }


  // 유저 삭제
  public void delete(Long id, PasswordDTO passwordDTO) {
    User user = userRepository.findByIdOrElseThrow(id);
    // 패스워드가 일치하지 않는다면
    if(!isValidPassword(passwordDTO.getPassword(), user)) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }

    userRepository.delete(user);
  }


  // 유저 로그인
  public LoginResponseDTO login(LoginRequestDTO requestDTO) {
    // email 일치 회원 조회
    User user = userRepository.findByEmailOrElseThrow(requestDTO.getEmail());

    // 패스워드가 일치하지 않는다면
    if(!isValidPassword(requestDTO.getPassword(), user)) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }

    return new LoginResponseDTO(user.getId());
  }

  // 저장된 비밀번호와 일치 여부
  private boolean isValidPassword(String password, User user) {
    return passwordEncoder.matches(password, user.getPassword());
  }

  // 이메일 중복 검사
  private boolean isExistsEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }
}

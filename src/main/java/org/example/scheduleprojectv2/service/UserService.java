package org.example.scheduleprojectv2.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.LoginRequestDTO;
import org.example.scheduleprojectv2.dto.LoginResponseDTO;
import org.example.scheduleprojectv2.dto.SignUpRequestDTO;
import org.example.scheduleprojectv2.dto.SignUpResponseDTO;
import org.example.scheduleprojectv2.dto.UserResponseDTO;
import org.example.scheduleprojectv2.dto.UserUpdateRequestDTO;
import org.example.scheduleprojectv2.entity.User;
import org.example.scheduleprojectv2.exception.CustomException;
import org.example.scheduleprojectv2.exception.ErrorCode;
import org.example.scheduleprojectv2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  // 유저 생성
  public SignUpResponseDTO signUp(SignUpRequestDTO requestDTO) {
    User savedUser = userRepository.save(new User(requestDTO));
    return new SignUpResponseDTO(savedUser);
  }

  // 유저 조회
  public UserResponseDTO findById(Long id) {
    return new UserResponseDTO(userRepository.findByIdOrElseThrow(id));
  }

  // 유저 수정
  public UserResponseDTO update(Long id, UserUpdateRequestDTO requestDTO) {
    User user = userRepository.findByIdOrElseThrow(id);
    user.update(requestDTO);
    return new UserResponseDTO(user);
  }

  // 유저 삭제
  public void delete(Long id) {
    User user = userRepository.findByIdOrElseThrow(id);
    userRepository.delete(user);
  }

  // 유저 로그인
  public LoginResponseDTO login(LoginRequestDTO requestDTO) {
    // email 일치 회원 조회
    // TODO: 레포지토리에서 USER_NOT_FOUND 에러 던지게 했는데 이게 맞을까?
    User user = userRepository.findByEmailOrElseThrow(requestDTO.getEmail());

    // 패스워드가 일치하지 않는다면
    if(!isValidPassword(user, requestDTO.getPassword())) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }

    return new LoginResponseDTO(user.getId());
  }

  // 저장된 비밀번호와 일치 여부
  private boolean isValidPassword(User user, String password) {
    return user.getPassword().equals(password);
  }
}

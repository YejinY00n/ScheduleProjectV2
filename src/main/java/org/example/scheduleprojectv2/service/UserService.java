package org.example.scheduleprojectv2.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.LoginRequestDTO;
import org.example.scheduleprojectv2.dto.LoginResponseDTO;
import org.example.scheduleprojectv2.dto.SignUpRequestDTO;
import org.example.scheduleprojectv2.dto.SignUpResponseDTO;
import org.example.scheduleprojectv2.dto.UserResponseDTO;
import org.example.scheduleprojectv2.dto.UserUpdateRequestDTO;
import org.example.scheduleprojectv2.entity.User;
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
    // TODO: 로그인 성공 시 리턴값 어떻게 할지 고려
    // email, password 일치 조회
    Long id = userRepository.findIdByEmailAndPasswordAndOrElseThrow(requestDTO.getEmail(), requestDTO.getPassword());
    return new LoginResponseDTO(id);
  }
}

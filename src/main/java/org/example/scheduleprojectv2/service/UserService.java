package org.example.scheduleprojectv2.service;

import lombok.RequiredArgsConstructor;
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

  // TODO: JWT 적용
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
    userRepository.deleteById(id);
  }
}

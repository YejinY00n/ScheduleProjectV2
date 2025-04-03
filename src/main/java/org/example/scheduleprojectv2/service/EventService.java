package org.example.scheduleprojectv2.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.EventCreateRequestDTO;
import org.example.scheduleprojectv2.dto.EventResponseDTO;
import org.example.scheduleprojectv2.dto.EventUpdateRequestDTO;
import org.example.scheduleprojectv2.dto.PasswordDTO;
import org.example.scheduleprojectv2.entity.Event;
import org.example.scheduleprojectv2.entity.User;
import org.example.scheduleprojectv2.exception.CustomException;
import org.example.scheduleprojectv2.exception.ErrorCode;
import org.example.scheduleprojectv2.repository.EventRepository;
import org.example.scheduleprojectv2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventService {
  private final EventRepository eventRepository;
  private final UserRepository userRepository;

  // 할일 생성
  public EventResponseDTO save(EventCreateRequestDTO requestDTO) {
    User user = userRepository.findByEmailOrElseThrow(requestDTO.getEmail());
    Event event = eventRepository.save(new Event(requestDTO, user));
    return new EventResponseDTO(event);
  }

  // 할일 단건 조회
  public EventResponseDTO findById(Long id) {
    return new EventResponseDTO(eventRepository.findByIdOrElseThrow(id));
  }

  // 할일 전체 조회
  public List<EventResponseDTO> findAll() {
    return eventRepository.findAll().stream()
        .map(EventResponseDTO::new)
        .toList();
  }

  // 할일 수정
  @Transactional
  public EventResponseDTO update(Long id, EventUpdateRequestDTO requestDTO) {
    Event event = eventRepository.findByIdOrElseThrow(id);

    if(!isValidPassword(event, requestDTO.getPassword())) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }
    event.update(requestDTO);
    return new EventResponseDTO(event);
  }


  // 할일 삭제
  public void delete(Long id, PasswordDTO passwordDTO) {
    Event event = eventRepository.findByIdOrElseThrow(id);
    // 패스워드가 일치하지 않는다면
    if(!isValidPassword(event, passwordDTO.getPassword())) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }
    eventRepository.delete(event);
  }

  // 비밀번호 검증  // TODO: 이거 공통 로직으로 처리하게 할 수 있나?
  private boolean isValidPassword(Event event, String password) {
    return event.getUser().getPassword().equals(password);
  }
}

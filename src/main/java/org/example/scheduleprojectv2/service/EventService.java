package org.example.scheduleprojectv2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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
  public List<EventResponseDTO> findAll(
      Long userId, LocalDateTime startDate, LocalDateTime endDate) {
    List<Event> results;
    User user = null;
    if(userId!=-1) {
      user = userRepository.findByIdOrElseThrow(userId);
    }

    if (isValidDateRange(startDate, endDate)) {
      // 작성자(이메일)의 기간 내의 일정 조회
      if (user != null) {
        results = eventRepository.findAllByUserAndModifiedAtBetween(user, startDate, endDate);
      }
      // 기간 내 모든 일정 조회
      else {
        results = eventRepository.findAllByModifiedAtBetween(startDate, endDate);
      }
    } else {
      // 작성자의 모든 일정 조회
      if (user != null) {
        results = eventRepository.findAllByUser(user);
      }
      // 모든 일정 조회 (조건x)
      else {
        results = eventRepository.findAll();
      }
    }

    // List<Event> --> List<EventResponseDTO>
    return results.stream()
        .map(EventResponseDTO::new)
        .collect(Collectors.toList());
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

  // 비밀번호 검증
  private boolean isValidPassword(Event event, String password) {
    return event.getUser().getPassword().equals(password);
  }

  // 유효한 기간인지 검증하는 메소드
  private boolean isValidDateRange(LocalDateTime startTime, LocalDateTime endTime) {
    // 조회 시작일, 끝일 중 하나라도 없다면
    if (startTime == null || endTime == null) {
      return false;
    }
    // 조회 시작일이 끝일보다 나중이라면
    else {
      return !startTime.isAfter(endTime);
    }
  }
}

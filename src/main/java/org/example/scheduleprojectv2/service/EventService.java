package org.example.scheduleprojectv2.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.CreateEventRequestDTO;
import org.example.scheduleprojectv2.dto.EventResponseDTO;
import org.example.scheduleprojectv2.entity.Event;
import org.example.scheduleprojectv2.entity.User;
import org.example.scheduleprojectv2.repository.EventRepository;
import org.example.scheduleprojectv2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
  private final EventRepository eventRepository;
  private final UserRepository userRepository;

  // 할일 생성
  public EventResponseDTO save(CreateEventRequestDTO requestDTO) {
    User user = userRepository.findUserByEmailOrElseThrow(requestDTO.getEmail());
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

  // 할일 삭제
}

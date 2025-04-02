package org.example.scheduleprojectv2.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.EventCreateRequestDTO;
import org.example.scheduleprojectv2.dto.EventResponseDTO;
import org.example.scheduleprojectv2.dto.EventUpdateRequestDTO;
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
  public EventResponseDTO save(EventCreateRequestDTO requestDTO) {
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
  public EventResponseDTO update(Long id, EventUpdateRequestDTO requestDTO) {
    Event event = eventRepository.findByIdOrElseThrow(id);
    event.update(requestDTO);
    return new EventResponseDTO(event);
  }

  // 할일 삭제
  public void delete(Long id) {
    Event event = eventRepository.findByIdOrElseThrow(id);
    eventRepository.delete(event);
  }
}

package org.example.scheduleprojectv2.service;

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
    System.out.println("BEFORE FIND USER!!!!");
    User user = userRepository.findUserByEmailOrElseThrow(requestDTO.getEmail());
    System.out.println("FIND USER AFTER!!!!");
    Event event = eventRepository.save(new Event(requestDTO, user));
    System.out.println("SAVE AFTER!!!");
    return new EventResponseDTO(event);
  }

  // 할일 조회

  // 할일 수정

  // 할일 삭제
}

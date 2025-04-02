package org.example.scheduleprojectv2.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.CreateEventRequestDTO;
import org.example.scheduleprojectv2.dto.EventResponseDTO;
import org.example.scheduleprojectv2.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
  private final EventService eventService;

  // 할일 생성
  @PostMapping("")
  public ResponseEntity<EventResponseDTO> create(@RequestBody CreateEventRequestDTO requestDTO) {
    return new ResponseEntity<EventResponseDTO>(eventService.save(requestDTO), HttpStatus.CREATED);
  }

  // 할일 조회

  // 할일 수정

  // 할일 삭제
}

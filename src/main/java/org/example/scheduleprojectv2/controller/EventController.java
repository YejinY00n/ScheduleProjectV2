package org.example.scheduleprojectv2.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.EventCreateRequestDTO;
import org.example.scheduleprojectv2.dto.EventResponseDTO;
import org.example.scheduleprojectv2.dto.EventUpdateRequestDTO;
import org.example.scheduleprojectv2.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<EventResponseDTO> create(@RequestBody EventCreateRequestDTO requestDTO) {
    return new ResponseEntity<>(eventService.save(requestDTO), HttpStatus.CREATED);
  }

  // 할일 단건 조회
  @GetMapping("/{id}")
  public ResponseEntity<EventResponseDTO> findById(@PathVariable Long id) {
    return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
  }

  // 할일 전체 조회
  @GetMapping()
  public List<EventResponseDTO> findAll() {
    return eventService.findAll();
  }

  // 할일 수정
  @PutMapping("/{id}")
  public ResponseEntity<EventResponseDTO> update(
      @PathVariable Long id, @RequestBody EventUpdateRequestDTO requestDTO) {
    return new ResponseEntity<>(eventService.update(id, requestDTO), HttpStatus.OK);
  }

  // 할일 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    eventService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

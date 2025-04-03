package org.example.scheduleprojectv2.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.CommentCreateRequestDTO;
import org.example.scheduleprojectv2.dto.CommentResponseDTO;
import org.example.scheduleprojectv2.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events/{eventId}/comments")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;

  // 댓글 작성
  @PostMapping()
  public ResponseEntity<CommentResponseDTO> create(
      @PathVariable Long eventId, CommentCreateRequestDTO requestDTO) {
    return new ResponseEntity<>(commentService.save(eventId, requestDTO), HttpStatus.CREATED);
  }

  // 댓글 조회

  // 댓글 수정

  // 댓글 삭제
}

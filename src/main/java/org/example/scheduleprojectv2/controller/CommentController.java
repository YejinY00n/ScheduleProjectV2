package org.example.scheduleprojectv2.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.CommentCreateRequestDTO;
import org.example.scheduleprojectv2.dto.CommentResponseDTO;
import org.example.scheduleprojectv2.dto.CommentUpdateRequestDTO;
import org.example.scheduleprojectv2.dto.PasswordDTO;
import org.example.scheduleprojectv2.service.CommentService;
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
@RequestMapping("/events/{eventId}/comments")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;

  // 댓글 작성
  @PostMapping()
  public ResponseEntity<CommentResponseDTO> create(
      @PathVariable Long eventId, @RequestBody CommentCreateRequestDTO requestDTO) {
    return new ResponseEntity<>(commentService.save(eventId, requestDTO), HttpStatus.CREATED);
  }

  // 일정의 전체 댓글 조회
  @GetMapping()
  public List<CommentResponseDTO> findAll(@PathVariable Long eventId) {
    return commentService.findAll(eventId);
  }

  // 댓글 수정
  @PutMapping("/{commentId}")
  public ResponseEntity<CommentResponseDTO> update(
      @PathVariable Long commentId,
      @RequestBody CommentUpdateRequestDTO requestDTO) {
    return new ResponseEntity<>(commentService.update(commentId, requestDTO), HttpStatus.OK);
  }

  // 댓글 삭제
  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> delete(
      @PathVariable Long commentId, @RequestBody PasswordDTO passwordDTO) {
    commentService.delete(commentId, passwordDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

package org.example.scheduleprojectv2.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.dto.CommentCreateRequestDTO;
import org.example.scheduleprojectv2.dto.CommentResponseDTO;
import org.example.scheduleprojectv2.entity.Comment;
import org.example.scheduleprojectv2.entity.Event;
import org.example.scheduleprojectv2.entity.User;
import org.example.scheduleprojectv2.repository.CommentRepository;
import org.example.scheduleprojectv2.repository.EventRepository;
import org.example.scheduleprojectv2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final UserRepository userRepository;
  private final EventRepository eventRepository;

  // 댓글 작성
  public CommentResponseDTO save(Long eventId, CommentCreateRequestDTO requestDTO) {
    User user = userRepository.findByEmailOrElseThrow(requestDTO.getEmail());
    Event event = eventRepository.findByIdOrElseThrow(eventId);
    Comment comment = commentRepository.save(new Comment(requestDTO, user, event));
    return new CommentResponseDTO(comment);
  }

  // 댓글 조회

  // 댓글 수정

  // 댓글 삭제
}

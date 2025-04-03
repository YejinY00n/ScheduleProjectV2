package org.example.scheduleprojectv2.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.scheduleprojectv2.config.PasswordEncoder;
import org.example.scheduleprojectv2.dto.CommentCreateRequestDTO;
import org.example.scheduleprojectv2.dto.CommentResponseDTO;
import org.example.scheduleprojectv2.dto.CommentUpdateRequestDTO;
import org.example.scheduleprojectv2.dto.PasswordDTO;
import org.example.scheduleprojectv2.entity.Comment;
import org.example.scheduleprojectv2.entity.Event;
import org.example.scheduleprojectv2.entity.User;
import org.example.scheduleprojectv2.exception.CustomException;
import org.example.scheduleprojectv2.exception.ErrorCode;
import org.example.scheduleprojectv2.repository.CommentRepository;
import org.example.scheduleprojectv2.repository.EventRepository;
import org.example.scheduleprojectv2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final UserRepository userRepository;
  private final EventRepository eventRepository;
  private final PasswordEncoder passwordEncoder;

  // 댓글 작성
  public CommentResponseDTO save(Long eventId, CommentCreateRequestDTO requestDTO) {
    User user = userRepository.findByEmailOrElseThrow(requestDTO.getEmail());
    Event event = eventRepository.findByIdOrElseThrow(eventId);
    Comment comment = commentRepository.save(new Comment(requestDTO, user, event));
    return new CommentResponseDTO(comment);
  }

  // 일정의 전체 댓글 조회
  public List<CommentResponseDTO> findAll(Long eventId) {
    Event event = eventRepository.findByIdOrElseThrow(eventId);
    return commentRepository.findAllByEvent(event).stream()
        .map(CommentResponseDTO::new).toList();
  }

  // 댓글 수정
  @Transactional
  public CommentResponseDTO update(Long commentId, CommentUpdateRequestDTO requestDTO) {
    Comment comment = commentRepository.findByIdOrElseThrow(commentId);

    if(!isValidPassword(requestDTO.getPassword(), comment)) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }
    comment.update(requestDTO);
    return new CommentResponseDTO(comment);
  }

  // 댓글 삭제
  public void delete(Long commentId, PasswordDTO passwordDTO) {
    Comment comment = commentRepository.findByIdOrElseThrow(commentId);

    if(!isValidPassword(passwordDTO.getPassword(), comment)) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }
    commentRepository.delete(comment);
  }

  // 비밀번호 검증
  public boolean isValidPassword(String password, Comment comment) {
    return passwordEncoder.matches(password, comment.getUser().getPassword());
  }
}

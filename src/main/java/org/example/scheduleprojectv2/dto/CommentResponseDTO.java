package org.example.scheduleprojectv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduleprojectv2.entity.Comment;

@Getter
@AllArgsConstructor
public class CommentResponseDTO {
  private final Long id;
  private final String content;
  private final String name;

  public CommentResponseDTO(Comment comment) {
    this.id = comment.getId();
    this.content = comment.getContent();
    this.name = comment.getUser().getName();
  }
}

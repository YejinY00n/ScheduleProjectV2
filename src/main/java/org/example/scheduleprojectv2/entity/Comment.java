package org.example.scheduleprojectv2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.scheduleprojectv2.dto.CommentCreateRequestDTO;
import org.example.scheduleprojectv2.dto.CommentUpdateRequestDTO;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Table(name = "comment")
public class Comment extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "event_id")
  private Event event;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(nullable = false)
  private String content;

  public Comment(CommentCreateRequestDTO requestDTO, User user, Event event) {
    this.content = requestDTO.getContent();
    this.user = user;
    this.event = event;
  }

  public Comment() {

  }

  public void update(CommentUpdateRequestDTO requestDTO) {
    this.content = requestDTO.getContent();
  }
}

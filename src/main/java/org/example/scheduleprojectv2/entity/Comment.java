package org.example.scheduleprojectv2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.scheduleprojectv2.dto.CommentCreateRequestDTO;

@Entity
@Getter
@Table(name = "comment")
public class Comment extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // TODO: @ManyToOne(cascade=CascadeType.ALL) 연관 관계 설정
  @Setter
  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;

  @Setter
  @ManyToOne
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
}

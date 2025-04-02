package org.example.scheduleprojectv2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.scheduleprojectv2.dto.CreateEventRequestDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@ToString
@Entity
@Getter
@Table(name = "event")
public class Event extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String task;

  // TODO: Base Entity 분리
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime modifiedAt;

  @Setter
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Event(CreateEventRequestDTO requestDTO, User user) {
    this.task = requestDTO.getTask();
    this.user = user;
  }

  public Event() {

  }
}

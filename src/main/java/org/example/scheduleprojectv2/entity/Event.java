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
import lombok.ToString;
import org.example.scheduleprojectv2.dto.EventCreateRequestDTO;
import org.example.scheduleprojectv2.dto.EventUpdateRequestDTO;

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

  @Setter
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Event(EventCreateRequestDTO requestDTO, User user) {
    this.task = requestDTO.getTask();
    this.user = user;
  }

  public Event() {

  }

  public void update(EventUpdateRequestDTO requestDTO) {
    this.task = requestDTO.getTask();
  }
}

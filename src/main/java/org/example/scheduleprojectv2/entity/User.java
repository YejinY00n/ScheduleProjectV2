package org.example.scheduleprojectv2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.example.scheduleprojectv2.dto.SignUpRequestDTO;
import org.example.scheduleprojectv2.dto.UserUpdateRequestDTO;

@Entity
@Getter
@Table(name="user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  public User() {

  }

  // SignUpRequestDTO --> Entity
  public User(SignUpRequestDTO requestDTO) {
    this.email = requestDTO.getEmail();
    this.password = requestDTO.getPassword();
    this.name = requestDTO.getName();
  }

  public void update(UserUpdateRequestDTO requestDTO) {
    this.email = requestDTO.getEmail();
    this.password = requestDTO.getPassword();
    this.name = requestDTO.getName();
  }

  public void updatePassword(String newPassword) {
    this.password = newPassword;
  }
}

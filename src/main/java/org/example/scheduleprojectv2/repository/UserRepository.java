package org.example.scheduleprojectv2.repository;

import java.util.Optional;
import org.example.scheduleprojectv2.entity.User;
import org.example.scheduleprojectv2.exception.CustomException;
import org.example.scheduleprojectv2.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByEmail(String name);
  Optional<User> findByEmailAndPassword(String email, String password);

  default User findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
  }

  default User findByEmailOrElseThrow(String email) {
    return findUserByEmail(email).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
  }
}

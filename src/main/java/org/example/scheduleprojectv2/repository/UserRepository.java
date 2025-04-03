package org.example.scheduleprojectv2.repository;

import java.util.Optional;
import org.example.scheduleprojectv2.entity.User;
import org.example.scheduleprojectv2.exception.CustomException;
import org.example.scheduleprojectv2.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByEmail(String name);
  Optional<User> findByEmailAndPassword(String email, String password);

  default User findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = "+id));
  }

  default User findUserByEmailOrElseThrow(String email) {
    return findUserByEmail(email).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
  }
}

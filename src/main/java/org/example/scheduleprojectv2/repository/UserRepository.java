package org.example.scheduleprojectv2.repository;

import java.util.Optional;
import org.example.scheduleprojectv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByEmail(String name);

  default User findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = "+id));
  }

  default User findUserByEmailOrElseThrow(String email) {
    return findUserByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Does not exist email = "+email));
  }
}

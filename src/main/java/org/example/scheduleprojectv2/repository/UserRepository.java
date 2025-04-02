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
  Optional<User> findByEmailAndPassword(String email, String password);

  default User findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = "+id));
  }

  default User findUserByEmailOrElseThrow(String email) {
    return findUserByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Does not exist email = "+email));
  }

  // TODO: 회원 정보 찾기 방식 변경 고려
  // 이거 그냥 email로 user 찾고 (elseThrow) 서비스 레이어에서 비밀번호 대조하는 방식으로 갈까??
  // email 조회 성공, password 실패 --> 비번 틀렸음
  // email 조회 실패 --> 존재하지 않는 회원
  default Long findIdByEmailAndPasswordAndOrElseThrow(String email, String password) {
    return findByEmailAndPassword(email, password).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getId();
  }
}

package org.example.scheduleprojectv2.repository;

import java.util.List;
import org.example.scheduleprojectv2.entity.Comment;
import org.example.scheduleprojectv2.entity.Event;
import org.example.scheduleprojectv2.exception.CustomException;
import org.example.scheduleprojectv2.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findAllByEvent(Event e);

  default Comment findByIdOrElseThrow(Long commentId) {
    return findById(commentId).orElseThrow(()->new CustomException(ErrorCode.COMMENT_NOT_FOUND));
  }
}

package com.sparta.week3.repository;


import com.sparta.week3.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByIdAndUserId(Long id, Long userId);

    List<Comment> findAllByBoardIdOrderByCreatedAtDesc(Long boardId);
}

package com.sparta.week3.repository;

import com.sparta.week3.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();   //  <<<<<<<<<<<<<<<  공부 필요

}

package com.sparta.week3.repository;

import com.sparta.week3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
//    Optional<User> findByKakaoId(Long kakaoId);
}
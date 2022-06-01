package com.sparta.week3.mock;

import com.sparta.week3.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockUserRepository {

    private List<User> users = new ArrayList<>();
    // user 테이블 ID: 1부터 시작
    private Long userId = 1L;

    // 회원 정보 저장
    public User save(User user) {
        user.setId(userId);
        ++userId;
        users.add(user);
        return user;
    }

    // 회원 이름으로 회원 조회
    public Optional<User> findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

}
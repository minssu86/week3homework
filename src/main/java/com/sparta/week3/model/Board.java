package com.sparta.week3.model;

import com.sparta.week3.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class Board extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;


//    public Board(String username, String subject, String contents) {
//        this.username = username;
//        this.subject = subject;
//        this.contents = contents;
//    }

    public Board(Long userId, BoardRequestDto requestDto) {
        this.subject = requestDto.getSubject();
        this.contents = requestDto.getContents();
        this.userId = userId;
    }


    public void update(Long userId, BoardRequestDto requestDto) {
        this.subject = requestDto.getSubject();
        this.contents = requestDto.getContents();
        this.userId = userId;
    }

}

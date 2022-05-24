package com.sparta.week3.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String contents;


    public Board(String username, String password, String subject, String contents) {
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.contents = contents;
    }

    public Board(BoardRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.subject = requestDto.getSubject();
        this.contents = requestDto.getContents();
    }


    public void update(BoardRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.subject = requestDto.getSubject();
        this.contents = requestDto.getContents();
    }

}

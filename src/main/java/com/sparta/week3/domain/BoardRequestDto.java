package com.sparta.week3.domain;

import lombok.Getter;

@Getter
public class BoardRequestDto {

    private String username;
    private String password;
    private String subject;
    private String contents;

}

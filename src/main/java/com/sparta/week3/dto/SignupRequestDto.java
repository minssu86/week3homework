package com.sparta.week3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
//    private boolean admin = false;
//    private String adminToken = "";
}

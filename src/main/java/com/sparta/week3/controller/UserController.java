package com.sparta.week3.controller;

import com.sparta.week3.dto.SignupRequestDto;
import com.sparta.week3.dto.UserInfoDto;
import com.sparta.week3.security.UserDetailsImpl;
import com.sparta.week3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(){
        return "signup";
    }


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model){
        String result = userService.registerUser(requestDto);
        if(result.equals("success")){
            return "redirect:/user/loginView";
        }
        model.addAttribute("msg", result);
        return "login";
    }


    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }


    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();

        return new UserInfoDto(username);
    }

}

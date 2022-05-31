package com.sparta.week3.service;

import com.sparta.week3.dto.SignupRequestDto;
import com.sparta.week3.model.User;
import com.sparta.week3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
//    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();


        // ID 유효성 검사 : 3자리 이상 확인
        if(username.length()<3){
            throw new IllegalArgumentException("아이디는 최소 3자리 이상입니다");
        } else {
            // ID 유효성 검사 : 알파벳 대소문자, 숫자로 구성 확인
            String pattern = "[a-zA-Z0-9]";
            String temp;
            for (int i=0; i<username.length(); i++){
                temp = ""+ username.charAt(i);
                if(!Pattern.matches(pattern, temp)){
                    throw new IllegalArgumentException("아이디는 영문 대소문자, 숫자만 가능합니다");
                }
            }
        }

        // ID 중복 확인
        try {
            Optional<User> usedId = userRepository.findByUsername(username);

            if(usedId.isPresent()){    // return value != null;
                throw new IllegalArgumentException("중복된 닉네임입니다.");
            }
        }catch (IllegalArgumentException e){
            return "중복된 닉네임입니다.";
        }



        // 비밀번호 유효성 검사
        if(requestDto.getPassword().length()<4){
            throw new IllegalArgumentException("비밀번호는 최소 4자리 이상입니다.");
        } else {
            if(requestDto.getPassword().contains(username)){
                throw new IllegalArgumentException("비밀번호에는 ID를 포함 할 수 없습니다.");
            }
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());  // 비밀번호 암호화

        String email = requestDto.getEmail();

        // 취합 후 반환
        User user = new User(username, password, email);
        userRepository.save(user);

        return "success";
    }
}

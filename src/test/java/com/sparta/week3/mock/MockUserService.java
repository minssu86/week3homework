package com.sparta.week3.mock;

import com.sparta.week3.dto.SignupRequestDto;
import com.sparta.week3.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.regex.Pattern;

public class MockUserService {

    private final MockUserRepository mockUserRepository;
//    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    public MockUserService(){
        this.mockUserRepository = new MockUserRepository();
    }

    public String registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();


        // ID 유효성 검사 : 3자리 이상 확인
        if(username.length()<3){
//            throw new IllegalArgumentException("아이디는 최소 3자리 이상입니다");
            return "아이디는 최소 3자리 이상입니다";
        } else {
            // ID 유효성 검사 : 알파벳 대소문자, 숫자로 구성 확인
            String pattern = "[a-zA-Z0-9]";
            String temp;
            for (int i=0; i<username.length(); i++){
                temp = ""+ username.charAt(i);
                if(!Pattern.matches(pattern, temp)){
//                    throw new IllegalArgumentException("아이디는 영문 대소문자, 숫자만 가능합니다");
                    return "아이디는 영문 대소문자, 숫자만 가능합니다";
                }
            }
        }

        // ID 중복 확인
        try {
            Optional<User> usedId = mockUserRepository.findByUsername(username);

            if(usedId.isPresent()){    // return value != null;
                throw new IllegalArgumentException("중복된 닉네임입니다.");
            }
        }catch (IllegalArgumentException e){
            return "중복된 닉네임입니다.";
        }

        // 비밀번호 유효성 검사
        if(requestDto.getPassword().length()<4){
//            throw new IllegalArgumentException("비밀번호는 최소 4자리 이상입니다.");
            return "비밀번호는 최소 4자리 이상입니다.";
        } else {
            if(requestDto.getPassword().contains(username)){
//                throw new IllegalArgumentException("비밀번호에는 ID를 포함 할 수 없습니다.");
                return "비밀번호에는 ID를 포함 할 수 없습니다.";
            }
        }

        // 패스워드 암호화
        PasswordEncoder passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return null;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return false;
            }
        };
        String password = passwordEncoder.encode(requestDto.getPassword());  // 비밀번호 암호화

        String email = requestDto.getEmail();

        // 취합 후 반환
        User user = new User(username, password, email);
        mockUserRepository.save(user);

        return "success";
    }
}

package com.sparta.week3.mock;

import com.sparta.week3.dto.SignupRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockUserServiceTest {
    @Test
    @DisplayName("정상 작동")
    void registerUser_Normal() {
        // given
        String username = "Tester01";
        String password = "testpass01";
        String email = "test01@sparta.com";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, email);

        MockUserService mockUserService = new MockUserService();

        // when
        String result = mockUserService.registerUser(requestDto);

        // then
        assertEquals("success", result);
    }

    @Test
    @DisplayName("닉네임 오류 - 3자 미만")
    void registerUser_Failed1() {
        // given
        String username = "te";
        String password = "testpass01";
        String email = "test01@sparta.com";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, email);

        MockUserService mockUserService = new MockUserService();

        // when
        String result = mockUserService.registerUser(requestDto);

        // then
        assertEquals("아이디는 최소 3자리 이상입니다", result);
    }

    @Test
    @DisplayName("닉네임 오류 - 허용되지 않은 글자")
    void registerUser_Failed2() {
        // given
        String username = "tester!!";
        String password = "testpass01";
        String email = "test01@sparta.com";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, email);

        MockUserService mockUserService = new MockUserService();

        // when
        String result = mockUserService.registerUser(requestDto);

        // then
        assertEquals("아이디는 영문 대소문자, 숫자만 가능합니다", result);
    }

    @Test
    @DisplayName("비밀번호 오류 - 4자 미만")
    void registerUser_Failed3() {
        // given
        String username = "Tester01";
        String password = "tes";
        String email = "test01@sparta.com";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, email);

        MockUserService mockUserService = new MockUserService();

        // when
        String result = mockUserService.registerUser(requestDto);

        // then
        assertEquals("비밀번호는 최소 4자리 이상입니다.", result);
    }

    @Test
    @DisplayName("비밀번호 오류 - 닉네임 포함")
    void registerUser_Failed4() {
        // given
        String username = "Tester01";
        String password = "Tester01password";
        String email = "test01@sparta.com";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, email);

        MockUserService mockUserService = new MockUserService();

        // when
        String result = mockUserService.registerUser(requestDto);

        // then
        assertEquals("비밀번호에는 ID를 포함 할 수 없습니다.", result);
    }

    @Test
    @DisplayName("중복 오류")
    void registerUser_Failed5() {
        // given
        // 기존 데이터 저장용
        String username = "Tester01";
        String password = "testpass01";
        String email = "test01@sparta.com";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, email);
        MockUserService mockUserService = new MockUserService();
        mockUserService.registerUser(requestDto);

        // 닉네임 중복 가입용
        username = "Tester01";
        password = "testpass02";
        email = "test02@sparta.com";

        requestDto.setUsername(username);
        requestDto.setPassword(password);
        requestDto.setEmail(email);

        // when
        String result = mockUserService.registerUser(requestDto);
        // then
        assertEquals("중복된 닉네임입니다.", result);
    }


}

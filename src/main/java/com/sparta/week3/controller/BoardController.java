package com.sparta.week3.controller;


import com.sparta.week3.model.Board;
import com.sparta.week3.dto.BoardRequestDto;
import com.sparta.week3.security.UserDetailsImpl;
import com.sparta.week3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;


//    @GetMapping("/")       // 스프링 프로젝트 구동 시 localhost:8080의 주소 값 기본이 index 페이지로 되어있음
//    public String home(){
//        return "index";
//    }


    // 게시글 작성
    @PostMapping("/api/boards")
    public Board createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardRequestDto requestDto) {
        Long userId = userDetails.getUser().getId();
        return boardService.createBoard(userId, requestDto);
    }


    // 게시글 목록 불러오기
    @GetMapping("/api/boards")
    public List<Board> getBoards() {
        return boardService.getBoards();
    }

    // 게시글 불러오기
    @GetMapping("/api/boards/board/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }


    // 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public String deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return boardService.deleteBoard(id, userId);
    }

    // 게시글 수정
    @PutMapping("/api/boards/{id}")
    public String  updateBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardRequestDto requestDto) {
        Long userId = userDetails.getUser().getId();
        return boardService.updateBoard(id, userId, requestDto);
    }


}

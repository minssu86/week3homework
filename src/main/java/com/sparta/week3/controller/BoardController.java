package com.sparta.week3.controller;


import com.sparta.week3.domain.Board;
import com.sparta.week3.domain.BoardRepository;
import com.sparta.week3.domain.BoardRequestDto;
import com.sparta.week3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;


//    @GetMapping("/")       // 스프링 프로젝트 구동 시 localhost:8080의 주소 값 기본이 index 페이지로 되어있음
//    public String home(){
//        return "index";
//    }

    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {   // @RequestBody 클라이언트가 보낸걸 넣어달라는 요청
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

    @GetMapping("/api/boards")
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        boardService.update(id, requestDto);
        return id;
    }


}

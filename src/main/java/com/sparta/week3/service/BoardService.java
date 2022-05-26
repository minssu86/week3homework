package com.sparta.week3.service;

import com.sparta.week3.domain.Board;
import com.sparta.week3.domain.BoardRepository;
import com.sparta.week3.domain.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;


    // 게시글 DB 저장
    public Board createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

    // DB로 부터 전체 게시글 불러오기
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    // 해당 게시글 삭제
    public String deleteBoard(Long id, BoardRequestDto requestDto) {
        if(requestDto.getPassword().equals(checkUserInfo(id).getPassword())){
            boardRepository.deleteById(id);
            return "삭제 성공";
        }
        return "비밀번호가 맞지 않습니다";
    }


    // 해당 게시글 수정
    @Transactional
    public String updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = checkUserInfo(id);
        if(requestDto.getPassword().equals(board.getPassword())){
            board.update(requestDto);
            return "수정 상공";
        }
        return "비밀번호가 맞지 않습니다";
    }


    // 비밀 번호 확인
    public Board checkUserInfo(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () ->  new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        return board;
    }

}


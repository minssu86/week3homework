package com.sparta.week3.service;

import com.sparta.week3.model.Board;
import com.sparta.week3.repository.BoardRepository;
import com.sparta.week3.dto.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;


    // 게시글 DB 저장
    public Board createBoard(Long userId, BoardRequestDto requestDto) {
        Board board = new Board(userId, requestDto);
        return boardRepository.save(board);
    }

    // DB로 부터 전체 게시글 불러오기
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }


    // DB로 부터 해당 게시글 불러오기
    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("존재하지 않는 글입니다.")
        );
    }


    // 해당 게시글 삭제
    public String deleteBoard(Long id, Long userId) {
        if(userId.equals(checkUserInfo(id).getUserId())){
            boardRepository.deleteById(id);
            return "삭제 성공";
        }
        return "작성자만 삭제 가능합니다";
    }


    // 해당 게시글 수정
    @Transactional
    public String updateBoard(Long id, Long userId, BoardRequestDto requestDto) {
        Board board = checkUserInfo(id);
        if(userId.equals(checkUserInfo(id).getUserId())){
            board.update(userId, requestDto);
            return "수정 성공";
        }
        return "작성자만 수정 가능합니다";
    }


    // 게시글 불러오기
    public Board checkUserInfo(Long id){
        return boardRepository.findById(id).orElseThrow(
                () ->  new IllegalArgumentException("존재하지 않는 글입니다.")
        );
    }

}


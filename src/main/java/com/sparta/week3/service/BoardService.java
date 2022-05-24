package com.sparta.week3.service;

import com.sparta.week3.domain.Board;
import com.sparta.week3.domain.BoardRepository;
import com.sparta.week3.domain.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional     // << 뭐임????
    public Long update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(requestDto.getPassword().equals(board.getPassword())){
            board.update(requestDto);
            return board.getId();
        }
        return 0L;
    }

}


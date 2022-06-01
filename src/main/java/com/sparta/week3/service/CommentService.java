package com.sparta.week3.service;

import com.sparta.week3.dto.CommentRequestDto;
import com.sparta.week3.model.Comment;
import com.sparta.week3.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 생성
    public String createComment(Long boardId, Long userId, CommentRequestDto requestDto) {
        if(requestDto.getSubject().length()<1){
            return "댓글 내용을 입력해주세요";
        }
        Comment comment = new Comment(boardId, userId, requestDto);
        commentRepository.save(comment);
        return  "success";
    }


    // 게시판 관련 댓글 불러 오기
    public List<Comment> getComments(Long boardId) {
        return commentRepository.findAllByBoardIdOrderByCreatedAtDesc(boardId);
    }

    // 댓글 삭제 : 작성자만 삭제 가능
    public String deleteBoard(Long id, Long userId) {
        if(checkComment(id, userId) == null){
            throw new NullPointerException("해당 댓글이 없습니다");
        }
        commentRepository.deleteById(id);
        return "댓글이 삭제 되었습니다.";
    }


    // 댓글 수정 : 작성자만 수정 가능
    @Transactional
    public String updateComment(Long id, Long userId, CommentRequestDto requestDto) {
        Comment comment = checkComment(id, userId);
        if(comment == null){
            throw new NullPointerException("해당 댓글이 없습니다");
        }
        if(requestDto.getSubject().length()<1){
            return "댓글 내용을 입력해주세요";
        }
       comment.update(comment.getBoardId(), userId, requestDto);
        return "수정 완료 하였습니다.";
    }

    // 댓글 작성자 확인 하여 DB에서 불러오기
    public Comment checkComment(Long id, Long userId){
        return commentRepository.findByIdAndUserId(id, userId);
    }

}



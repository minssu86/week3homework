package com.sparta.week3.controller;

import com.sparta.week3.dto.CommentRequestDto;
import com.sparta.week3.model.Comment;
import com.sparta.week3.security.UserDetailsImpl;
import com.sparta.week3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    // 댓글 작성
    @PostMapping("/api/comments/{id}")   // id = boardId
    public List<Comment> createComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto){
        Long userId = userDetails.getUser().getId();
        return  commentService.createComment(id, userId, requestDto);
    }

    // 댓글 수정
    @PutMapping("/api/comments/comment/{commentId}")
    public String updateComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto){
        Long userId = userDetails.getUser().getId();
        return commentService.updateComment(commentId, userId, requestDto);
    }


    // 댓글 삭제
    @DeleteMapping("api/comments/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return commentService.deleteBoard(commentId, userId);
    }


    // 댓글 조회
    @GetMapping("/api/comments/{id}")
    public List<Comment> getComments(@PathVariable Long id){
        return commentService.getComments(id);
    }

}

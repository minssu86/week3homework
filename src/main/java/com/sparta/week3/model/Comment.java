package com.sparta.week3.model;

import com.sparta.week3.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String subject;

    @Column
    private Long userId;

    @Column
    private Long boardId;

    public Comment(Long boardId, Long userId, CommentRequestDto requestDto){
        this.userId = userId;
        this.boardId = boardId;
        this.subject = requestDto.getSubject();
    }

    public void update(Long boardId, Long userId, CommentRequestDto requestDto) {
        this.userId = userId;
        this.boardId = boardId;
        this.subject = requestDto.getSubject();
    }
}

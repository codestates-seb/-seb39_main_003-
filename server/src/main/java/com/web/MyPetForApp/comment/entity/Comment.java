package com.web.MyPetForApp.comment.entity;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String commentContent;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    // Comment-Board 양방향 연관관계 편의 메서드
    public void setBoard(Board board){
        if(this.board != null){
            this.board.getComments().remove(this);
        }
        this.board = board;
        if(!board.getComments().contains(this)){
            board.addComment(this);
        }
    }
}

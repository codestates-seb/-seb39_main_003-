package com.web.MyPetForApp.comment.entity;

import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.entity.BoardCategory;
import com.web.MyPetForApp.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    public void changeMember(Member member) {
        this.member = member;
    }
    public void changeBoard(Board board) {
        this.board = board;
    }

    public void updateCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    // Comment-Board 양방향 연관관계 편의 메서드
//    public void setBoard(Board board){
//        if(this.board != null){
//            this.board.getComments().remove(this);
//        }
//        this.board = board;
//        if(!board.getComments().contains(this)){
//            board.addComment(this);
//        }
//    }
}

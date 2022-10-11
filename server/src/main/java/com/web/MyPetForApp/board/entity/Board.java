package com.web.MyPetForApp.board.entity;

import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.order.entity.OrderItem;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String boardContent;

    @Column(nullable = false)
    @Builder.Default
    private int view = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private BoardCategory boardCategory;

    // Board-Comment 양방향 연관관계 편의 메서드
//    public void addComment(Comment comment){
//        this.comments.add(comment);
//        if(comment.getBoard() != this){
//            comment.changeBoard(this);
//        }
//    }

    public void changeMember(Member member) {
        this.member = member;
    }
    public void changeBoardCategory(BoardCategory boardCategory) {
        this.boardCategory = boardCategory;
    }

    public void updateBoard(Board board) {
        Optional.ofNullable(board.getTitle())
                .ifPresent(title -> this.title = title);
        Optional.ofNullable(board.getBoardContent())
                .ifPresent(boardContent -> this.boardContent = boardContent);
    }

    public void addViewCnt() {
        this.view++;
    }
//    // Board-BoardTag 양방향 연관관계 편의 메서드
//    public void addBoardTag(BoardTag boardTag){
//        this.boardTags.add(boardTag);
//        if(boardTag.getBoard() != this){
//            boardTag.setBoard(this);
//        }
//    }
}

package com.web.MyPetForApp.board.entity;

import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.member.entity.Member;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String boardContent;

    @Column(nullable = false)
    private int view;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "BOARD_CATEGORY_ID")
    private BoardCategory boardCategory;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    // Board-Comment 양방향 연관관계 편의 메서드
    public void addComment(Comment comment){
        this.comments.add(comment);
        if(comment.getBoard() != this){
            comment.setBoard(this);
        }
    }
    // Board-BoardCategory 양방향 연관관계 편의 메서드
    public void setBoardCategory(BoardCategory boardCategory){
        if(this.boardCategory != null){
            this.boardCategory.getBoards().remove(this);
        }
        this.boardCategory = boardCategory;
        if(!boardCategory.getBoards().contains(this)){
            boardCategory.addBoard(this);
        }
    }
}

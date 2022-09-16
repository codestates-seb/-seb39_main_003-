package com.web.MyPetForApp.board.entity;

import com.web.MyPetForApp.board.basetime.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardTagId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    // BoardTag-Board 양방향 연관관계 편의 메서드
//    public void setBoard(Board board){
//        if (this.board != null) {
//            this.board.getBoardTags().remove(this);
//        }
//        this.board = board;
//        if(!board.getBoardTags().contains(this)){
//            board.addBoardTag(this);
//        }
//    }
//
//    // Tag-Board 양방향 연관관계 편의 메서드
//    public void setTag(Tag tag){
//        if(this.tag != null){
//            this.tag.getBoardTags().remove(this);
//        }
//        this.tag = tag;
//        if(!tag.getBoardTags().contains(this)){
//            tag.addBoardTags(this);
//        }
//    }
}

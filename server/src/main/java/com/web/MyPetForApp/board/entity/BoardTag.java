package com.web.MyPetForApp.board.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class BoardTag {
    private Long boardTagId;
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;
    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    // BoardTag-Board 양방향 연관관계 편의 메서드
    public void setBoard(Board board){
        if (this.board != null) {
            this.board.getBoardTags().remove(this);
        }
        this.board = board;
        if(!board.getBoardTags().contains(this)){
            board.addBoardTag(this);
        }
    }

    // Tag-Board 양방향 연관관계 편의 메서드
    public void setTag(Tag tag){
        if(this.tag != null){
            this.tag.getBoardTags().remove(this);
        }
        this.tag = tag;
        if(!tag.getBoardTags().contains(this)){
            tag.addBoardTags(this);
        }
    }
}

package com.web.MyPetForApp.board.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TagId;
    @Column(nullable = false, unique = true)
    private String tagName;

    @OneToMany(mappedBy = "tag")
    private List<BoardTag> boardTags = new ArrayList<>();

    // Tag-BoardTag 양방향 연관관계 편의 메서드
    public void addBoardTags(BoardTag boardTag){
        this.boardTags.add(boardTag);
        if(boardTag.getTag() != this){
            boardTag.setTag(this);
        }
    }
}

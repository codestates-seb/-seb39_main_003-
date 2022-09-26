package com.web.MyPetForApp.board.entity;

import com.web.MyPetForApp.board.basetime.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    private Long tagId;
    @Column(nullable = false, unique = true)
    private String tagName;
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<BoardTag> boardTags = new ArrayList<>();

//    // Tag-BoardTag 양방향 연관관계 편의 메서드
//    public void addBoardTags(BoardTag boardTag){
//        this.boardTags.add(boardTag);
//        if(boardTag.getTag() != this){
//            boardTag.setTag(this);
//        }
//    }
}
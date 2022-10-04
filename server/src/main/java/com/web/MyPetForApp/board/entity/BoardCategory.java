package com.web.MyPetForApp.board.entity;

import com.web.MyPetForApp.item.entity.Item;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategory {
    @Id
    private Long categoryId;
    @Column(nullable = false, unique = true)
    private String categoryName;
    @Column(nullable = false)
    private Integer pid;
    @Builder.Default
    @OneToMany(mappedBy = "boardCategory")
    private List<Board> boards = new ArrayList<>();

//    // Tag-BoardTag 양방향 연관관계 편의 메서드
//    public void addBoardTags(BoardTag boardTag){
//        this.boardTags.add(boardTag);
//        if(boardTag.getTag() != this){
//            boardTag.setTag(this);
//        }
//    }
}
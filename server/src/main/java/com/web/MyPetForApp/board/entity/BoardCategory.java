package com.web.MyPetForApp.board.entity;

import com.web.MyPetForApp.cartitem.entity.CartItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class BoardCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardCategoryId;
    @Column(nullable = false, unique = true)
    private String boardCategory;

    @OneToMany(mappedBy = "boardCategory")
    private List<Board> boards = new ArrayList<>();

    // BoardCategory-Board 양방향 연관관계 편의 메서드
    public void addBoard(Board board){
        this.boards.add(board);
        if(board.getBoardCategory() != this){
            board.setBoardCategory(this);
        }
    }
}

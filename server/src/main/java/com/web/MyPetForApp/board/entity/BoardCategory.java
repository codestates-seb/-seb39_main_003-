package com.web.MyPetForApp.board.entity;

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
}

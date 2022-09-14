package com.web.MyPetForApp.board.entity;

import javax.persistence.*;

@Entity
public class BoardCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardCategoryId;
    @Column
    private String boardCategory;
}

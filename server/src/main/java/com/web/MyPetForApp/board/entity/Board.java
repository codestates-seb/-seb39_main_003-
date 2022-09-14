package com.web.MyPetForApp.board.entity;

import com.web.MyPetForApp.member.entity.Member;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String title;

    @Column
    private String boardContent;

    @Column
    private int view;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp modifiedAt;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "BOARD_CATEGORY_ID")
    private BoardCategory boardCategory;
}

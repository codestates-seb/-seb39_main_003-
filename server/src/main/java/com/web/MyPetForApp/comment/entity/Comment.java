package com.web.MyPetForApp.comment.entity;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.member.entity.Member;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private String commentContent;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp modifiedAt;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;
}

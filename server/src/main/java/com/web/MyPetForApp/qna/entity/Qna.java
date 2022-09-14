package com.web.MyPetForApp.qna.entity;


import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    @Column
    private boolean checked;

    @Column
    private String qnaTitle;

    @Column
    private String qnaContent;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp modifiedAt;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}

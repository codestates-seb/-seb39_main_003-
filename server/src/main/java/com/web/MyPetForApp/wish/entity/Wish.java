package com.web.MyPetForApp.wish.entity;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;

import javax.persistence.*;

@Entity
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}

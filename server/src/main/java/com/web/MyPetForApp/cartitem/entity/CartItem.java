package com.web.MyPetForApp.cartitem.entity;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @Column
    private int itemCnt;

    @Column
    private int totalPrice;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp modifiedAt;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}

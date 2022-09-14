package com.web.MyPetForApp.item.entity;

import com.web.MyPetForApp.member.entity.Member;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column
    private String image;

    @Column
    private String itemName;

    @Column
    private int price;

    @Column
    private int soldCnt;

    @Column
    private int stockCnt;

    @Column
    private String info;

    @Column
    private String itemContent;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp modifiedAt;

    @Column
    private int wishCnt;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "ITEM_CATEGORY_ID")
    private ItemCategory itemCategory;
    @OneToOne
    @JoinColumn(name = "ITEM_INFO_ID")
    private ItemInfo itemInfo;
}

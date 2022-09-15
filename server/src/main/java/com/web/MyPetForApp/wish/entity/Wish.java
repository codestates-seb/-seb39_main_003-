package com.web.MyPetForApp.wish.entity;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    // Member 양방향 연관관계 편의 메서드
    public void addMember(Member member){
        this.member = member;
        if(!this.member.getWishes().contains(this)){
            this.member.getWishes().add(this);
        }
    }
    // Item 양방향 연관관계 편의 메서드
    public void addItem(Item item){
        this.item = item;
        if(!this.item.getWishes().contains(this)){
            this.item.getWishes().add(this);
        }
    }
}

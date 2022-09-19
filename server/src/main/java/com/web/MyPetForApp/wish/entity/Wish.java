package com.web.MyPetForApp.wish.entity;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    // Member-Wish 양방향 연관관계 편의 메서드
    public void setMember(Member member){
        if(this.member != null){
            this.member.getWishes().remove(this);
        }
        this.member = member;
        if(!member.getWishes().contains(this)){
            member.addWish(this);
        }
    }
    // Item-Wish 양방향 연관관계 편의 메서드
    public void setItem(Item item){
        if(this.item != null){
            this.item.getWishes().remove(this);
        }
        this.item = item;
        if(!item.getWishes().contains(this)){
            item.addWish(this);
        }
    }
}

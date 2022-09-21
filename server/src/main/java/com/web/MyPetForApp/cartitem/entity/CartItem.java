package com.web.MyPetForApp.cartitem.entity;

import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @Column(nullable = false)
    private int itemCnt;

//    @Column(nullable = false)
//    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    // CartItem-Member 양방향 연관관계 편의 메서드
    public void changeMember(Member member){
        if(this.member != null){
            this.member.getCartItems().remove(this);
        }
        this.member = member;
        if(!member.getCartItems().contains(this)){
            member.addCartItem(this);
        }
    }
    // CartItem-Item 양방향 연관관계 편의 메서드
    public void changeItem(Item item){
        if(this.item != null){
            this.item.getCartItems().remove(this);
        }
        this.item = item;
        if(!item.getCartItems().contains(this)){
            item.addCartItem(this);
        }
    }

    public void updateCnt(int itemCnt){
        if(itemCnt != 0) this.itemCnt = itemCnt;
    }
}

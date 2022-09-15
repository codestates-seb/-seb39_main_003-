package com.web.MyPetForApp.cartitem.entity;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @Column(nullable = false)
    private int itemCnt;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    // CartItem-Member 양방향 연관관계 편의 메서드
    public void setMember(Member member){
        if(this.member != null){
            this.member.getCartItems().remove(this);
        }
        this.member = member;
        if(!member.getCartItems().contains(this)){
            member.addCartItem(this);
        }
    }
    // CartItem-Item 양방향 연관관계 편의 메서드
    public void setItem(Item item){
        if(this.item != null){
            this.item.getCartItems().remove(this);
        }
        this.item = item;
        if(!item.getCartItems().contains(this)){
            item.addCartItem(this);
        }
    }
}

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

    // Member-CartItem 양방향 연관관계 편의 메서드
    public void addMember(Member member){
        this.member = member;
        if(!this.member.getCartItems().contains(this)){
            this.member.getCartItems().add(this);
        }
    }
    // Item-CartItem 양방향 연관관계 편의 메서드
    public void addItem(Item item){
        this.item = item;
        if(!this.item.getCartItems().contains(this)){
            this.item.getCartItems().add(this);
        }
    }
}

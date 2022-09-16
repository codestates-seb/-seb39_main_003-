package com.web.MyPetForApp.item.entity;

import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.order.entity.OrderItem;
import com.web.MyPetForApp.qna.entity.Qna;
import com.web.MyPetForApp.review.entity.Review;
import com.web.MyPetForApp.wish.entity.Wish;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int soldCnt;

    @Column(nullable = false)
    private int stockCnt;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private String itemContent;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp modifiedAt;

    @Column(nullable = false)
    private int wishCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_CATEGORY_ID")
    private ItemCategory itemCategory;

    @OneToMany(mappedBy = "item")
    private List<Wish> wishes = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Qna> qnas = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private  List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    // Item-Wish 양방향 연관관계 편의 메서드
    public void addWish(Wish wish){
        this.wishes.add(wish);
        if(wish.getItem() != this){
            wish.setItem(this);
        }
    }
    // Item-Order 양방향 연관관계 편의 메서드
    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        if(orderItem.getItem() != this){
            orderItem.setItem(this);
        }
    }
    // Item-CartItem 양방향 연관관계 편의 메서드
    public void addCartItem(CartItem cartItem){
        this.cartItems.add(cartItem);
        if(cartItem.getItem() != this){
            cartItem.setItem(this);
        }
    }

    // Board-BoardCategory 양방향 연관관계 편의 메서드
    public void setItemCategory(ItemCategory itemCategory){
        if(this.itemCategory != null){
            this.itemCategory.getItems().remove(this);
        }
        this.itemCategory = itemCategory;
        if(!itemCategory.getItems().contains(this)){
            itemCategory.addItem(this);
        }
    }
}

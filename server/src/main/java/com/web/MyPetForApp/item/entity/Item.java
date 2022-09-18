package com.web.MyPetForApp.item.entity;

import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.order.entity.OrderItem;
import com.web.MyPetForApp.qna.entity.Qna;
import com.web.MyPetForApp.review.entity.Review;
import com.web.MyPetForApp.wish.entity.Wish;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseTimeEntity {
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

    @Column(nullable = false) // wishes.size()와 같음
    private int wishCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_CATEGORY_ID")
    private ItemCategory itemCategory;

    @OneToMany(mappedBy = "item")
    private List<Wish> wishes = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Qna> qnas = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private  List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Item(String image, String itemName, int price, int soldCnt, int stockCnt, String info) {
        this.image = image;
        this.itemName = itemName;
        this.price = price;
        this.soldCnt = soldCnt;
        this.stockCnt = stockCnt;
        this.info = info;
    }

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
    public void setMember(Member member){
        this.member = member;
    }
    public void update(Item item){
        if(item.getImage() != null) this.image = item.image;
        if(item.getItemName() != null) this.itemName = item.getItemName();
        if(item.getInfo() != null) this.info = item.getInfo();
        if(item.price != 0) this.price = item.getPrice();
        if(item.stockCnt != 0) this.stockCnt = item.getStockCnt();
    }
}

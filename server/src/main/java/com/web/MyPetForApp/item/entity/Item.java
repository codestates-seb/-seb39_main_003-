package com.web.MyPetForApp.item.entity;

import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.qna.entity.Question;
import com.web.MyPetForApp.review.entity.Review;
import com.web.MyPetForApp.wish.entity.Wish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseTimeEntity {

    @Id
    private String itemId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private int soldCnt;

    @Column(nullable = false)
    private Integer stockCnt;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false) // wishes.size()와 같음
    private int wishCnt;

    @Column
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_CATEGORY_ID")
    private ItemCategory itemCategory;

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Wish> wishes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Question> qnas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public void addWish(Wish wish){
        this.wishes.add(wish);
        if(wish.getItem() != this){
            wish.changeItem(this);
        }
    }
    // Item-CartItem 양방향 연관관계 편의 메서드

    public void addCartItem(CartItem cartItem){
        this.cartItems.add(cartItem);
        if(cartItem.getItem() != this){
            cartItem.changeItem(this);
        }
    }

    // Board-BoardCategory 양방향 연관관계 편의 메서드
    public void changeItemCategory(ItemCategory itemCategory){
        if(this.itemCategory != null){
            this.itemCategory.getItems().remove(this);
        }
        this.itemCategory = itemCategory;
        if(!itemCategory.getItems().contains(this)){
            itemCategory.addItem(this);
        }
    }
    public void changeMember(Member member){
        this.member = member;
    }

    public void changeThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void updateItem(Item item){
        if(item.getItemName() != null) this.itemName = item.getItemName();
        if(item.getInfo() != null) this.info = item.getInfo();
        if(item.price != null) this.price = item.getPrice();
        if(item.stockCnt != null) this.stockCnt = item.getStockCnt();
    }
    public void updateWishCnt(){
        this.wishCnt = this.wishes.size();
    }
    public void updateStockAndSoldCnt(int orderItemCnt){
        if(this.stockCnt>=orderItemCnt)
            this.stockCnt -= orderItemCnt;
        else
            throw new BusinessLogicException(ExceptionCode.OUT_OF_STOCK);
        this.soldCnt += orderItemCnt;
    }
//    public void resetItemImages(){
//        this.itemImages = new ArrayList<>();
//    }
}

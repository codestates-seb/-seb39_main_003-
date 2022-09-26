package com.web.MyPetForApp.item.entity;

import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.qna.entity.Qna;
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
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long itemId;

    @Id
    private String itemId;

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

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Wish> wishes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Qna> qnas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

//    @Builder.Default
//    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
//    private List<ItemImage> itemImages = new ArrayList<>();

//    @Transient
//    private List<String> itemImagess = new ArrayList<>();

    // Item-Wish 양방향 연관관계 편의 메서드
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
//    public void addItemImage(ItemImage itemImage){
//        this.itemImages.add(itemImage);
//        if(itemImage.getItem() != this){
//            itemImage.changeItem(this);
//        }
//    }
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

    public void updateItem(Item item){
        if(item.getItemName() != null) this.itemName = item.getItemName();
        if(item.getInfo() != null) this.info = item.getInfo();
        if(item.price != 0) this.price = item.getPrice();
        if(item.stockCnt != 0) this.stockCnt = item.getStockCnt();
    }
    public void updateWishCnt(){
        this.wishCnt = this.wishes.size();
    }
//    public void resetItemImages(){
//        this.itemImages = new ArrayList<>();
//    }
}

package com.web.MyPetForApp.member.entity;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.order.entity.Order;
import com.web.MyPetForApp.qna.entity.Qna;
import com.web.MyPetForApp.review.entity.Review;
import com.web.MyPetForApp.wish.entity.Wish;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String memberName;

//    @Column(nullable = false)
    private String nickName;

//    @Column(nullable = false, unique = true)
    private String email;

//    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String address;

//    @Column(nullable = false, unique = true)
    private String phone;

//    @Column
    private String profileImg;

//    @Column
    private String roles;

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Wish> wishes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<CartItem> cartItems = new ArrayList<>();

    // Member-Order 양방향 연관관계 편의 메서드
    public void addOrder(Order order){
        orders.add(order);
        if(order.getMember() != this){
            order.setMember(this);
        }
    }
    // Member-Wish 양방향 연관관계 편의 메서드
    public void addWish(Wish wish){
        wishes.add(wish);
        if(wish.getMember() != this){
            wish.setMember(this);
        }
    }
    //Member-CartItem 양방향 연관관계 편의 메서드
    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
        if(cartItem.getMember() != this){
            cartItem.setMember(this);
        }
    }

    public void update(Member member) {
        this.nickName = member.getNickName();
        this.address = member.getAddress();
        this.password = member.getPassword();
        this.phone = member.getPhone();
        this.profileImg = member.getProfileImg();
    }

//    @OneToMany(mappedBy = "member") // 게시글 목록 필요??
//    private List<Board> boards = new ArrayList<>();

//    @OneToMany(mappedBy = "member") // 댓글 목록 필요??
//    private List<Comment> comments = new ArrayList<>();

//    @OneToMany(mappedBy = "member") // 문의 목록 필요??
//    private List<Qna> qnas = new ArrayList<>();


//    @OneToMany(mappedBy = "member") // 후기 목록 필요??
//    private List<Review> reviews = new ArrayList<>();

//    @OneToMany(mappedBy = "member") // 상품 목록 필요??
//    private List<Item> items = new ArrayList<>();
}

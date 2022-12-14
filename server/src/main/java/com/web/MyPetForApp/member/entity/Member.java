package com.web.MyPetForApp.member.entity;

import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.order.entity.Order;
import com.web.MyPetForApp.pay.entity.Pay;
import com.web.MyPetForApp.wish.entity.Wish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Member {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long memberId;

    @Id
    private String memberId;

    @Column(nullable = false)
    private String memberName;

    private String nickName;

//    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String address;

//    @Column(nullable = false, unique = true)
    private String phone;

    private String profileImg;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    public enum MemberRole {

        ROLE_USER("일반 사용자"),
        ROLE_ADMIN("관리자");

        @Getter
        private final String role;
        MemberRole(String role) {
            this.role = role;
        }
    }

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Wish> wishes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<CartItem> cartItems = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Pay> pays = new ArrayList<>();

    // Member-Order 양방향 연관관계 편의 메서드
    public void addOrder(Order order){
        orders.add(order);
        if(order.getMember() != this){
            order.changeMember(this);
        }
    }
    // Member-Wish 양방향 연관관계 편의 메서드
    public void addWish(Wish wish){
        wishes.add(wish);
        if(wish.getMember() != this){
            wish.changeMember(this);
        }
    }
    //Member-CartItem 양방향 연관관계 편의 메서드
    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
        if(cartItem.getMember() != this){
            cartItem.changeMember(this);
        }
    }
    // Order - Pay 양방향 연관관계 편의 메서드
    public void addPay(Pay pay) {
        this.pays.add(pay);
        if(pay.getMember() != this) {
            pay.changeMember(this);
        }
    }

    public void updateRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }
    public Member addMemberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateProfileImg(String profileImg) {this.profileImg = profileImg;}

    public Member updateMember(Member member) {
        Optional.ofNullable(member.getNickName()).ifPresent(
                name -> this.nickName = name);
        Optional.ofNullable(member.getAddress()).ifPresent(
                address -> this.address = address);
        Optional.ofNullable(member.getPassword()).ifPresent(
                password -> this.password = password);
        Optional.ofNullable(member.getPhone()).ifPresent(
                phone -> this.phone = phone);
        Optional.ofNullable(member.getProfileImg()).ifPresent(
                profileImg -> this.profileImg = profileImg);
        return this;
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

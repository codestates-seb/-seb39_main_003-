package com.web.MyPetForApp.member.entity;

import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.order.entity.Order;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String memberName;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column
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

    public void updateRole(MemberRole memberRole) {
        this.memberRole = memberRole;
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

    public void updateMember(Member member) {
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

package com.web.MyPetForApp.order.entity;

import com.web.MyPetForApp.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ORDERS")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String orderStatus;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp modifiedAt;

    @Column
    private String newAddress;

    @Column
    private String newPhone;

    @Column
    private String newName;

    @Column
    private String requirement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public void setMember(Member member){
        if(this.member != null){
            this.member.getOrders().remove(this);
        }
        this.member = member;
        if(!member.getOrders().contains(this)){
            member.addOrder(this);
        }
    }
    // Order - OrderItem 양방향 연관관계 편의 메서드
    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        if(orderItem.getOrder() != this){
            orderItem.setOrder(this);
        }
    }
}

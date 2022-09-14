package com.web.MyPetForApp.order.entity;

import com.web.MyPetForApp.member.entity.Member;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column
    private String orderStatus;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp modifiedAt;

    @Column
    private String newAddress;

    @Column
    private String newPhone;

    @Column
    private String newName;

    @Column
    private String requirement;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}

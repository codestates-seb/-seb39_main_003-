package com.web.MyPetForApp.order.entity;

import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.pay.entity.Pay;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ORDERS")
@Getter
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAY_ID")
    private Pay pay;

    public void changeMember(Member member){
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
            orderItem.changeOrder(this);
        }
    }

    public void changePay(Pay pay) {
        this.pay = pay;
        if (pay.getOrder() != this) {
            pay.addOrder(this);
        }
    }

    public void changeOrderItems(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }
    public enum OrderStatus{
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 접수"),
        PAY_WAIT(3, "결제 대기"),
        ORDER_COMPLETE(4, "주문 완료"),
        ORDER_CANCEL(4, "주문 취소");
        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
    public void updateOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }
    public void resetInfo(Member member){
        this.newAddress = member.getAddress();
        this.newName = member.getMemberName();
        this.newPhone = member.getPhone();
        this.requirement = "배송 전 연락 부탁드립니다.";
    }
}

package com.web.MyPetForApp.order.entity;

import com.web.MyPetForApp.item.entity.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @Column(nullable = false)
    private int orderItemCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    // Order-OrderItem 양방향 연관관계 편의 메서드
    public void setOrder(Order order){
        if(this.order != null){
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
        if(!order.getOrderItems().contains(this)){
            order.addOrderItem(this);
        }
    }
    // Item-OrderItem 양방향 연관관계 편의 메서드
    public void setItem(Item item){
        if(this.item != null){
            this.item.getOrderItems().remove(this);
        }
        this.item = item;
        if(!item.getOrderItems().contains(this)){
            item.addOrderItem(this);
        }
    }
}

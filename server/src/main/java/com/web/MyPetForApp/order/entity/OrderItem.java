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

    @Column
    private Long snapshotItemId;

    @Column
    private String snapshotItemName;

    @Column
    private int snapshotPrice;

    @Column
    private String snapshotImage;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    // Order-OrderItem 양방향 연관관계 편의 메서드
    public void changeOrder(Order order){
        if(this.order != null){
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
        if(!order.getOrderItems().contains(this)){
            order.addOrderItem(this);
        }
    }
}

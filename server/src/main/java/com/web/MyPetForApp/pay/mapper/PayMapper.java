package com.web.MyPetForApp.pay.mapper;

import com.web.MyPetForApp.order.dto.OrderDto;
import com.web.MyPetForApp.pay.dto.PayDto;
import com.web.MyPetForApp.pay.entity.Pay;
import org.springframework.stereotype.Component;

@Component
public class PayMapper {
    public Pay payPostToPay(PayDto.Post post) {
        return Pay.builder()
                .amount(post.getAmount())
                .payBy(Pay.PayBy.of(post.getPayBy()))
                .build();
    }
    public OrderDto.Post payPostToOrderPost(PayDto.Post post){
        return OrderDto.Post.builder()
                .orderItems(post.getOrderItems())
                .newAddress(post.getNewAddress())
                .newName(post.getNewName())
                .newPhone(post.getNewPhone())
                .requirement(post.getRequirement())
                .build();
    }

    public PayDto.Response payToResponse(Pay pay) {
        return PayDto.Response.builder()
                .PayId(pay.getPayId())
                .amount(pay.getAmount())
                .memberId(pay.getMember().getMemberId())
                .payBy(pay.getPayBy().getPayDescription())
                .PayStatus(pay.getPayStatus().getStepDescription())
                .createdAt(pay.getCreatedAt())
                .orderId(pay.getOrder().getOrderId())
                .build();
    }
}

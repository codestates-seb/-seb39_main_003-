package com.web.MyPetForApp.pay.mapper;

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

    public PayDto.Response payToResponse(Pay pay) {
        return PayDto.Response.builder()
                .PayId(pay.getPayId())
                .amount(pay.getAmount())
                .memberId(pay.getMember().getMemberId())
                .payBy(pay.getPayBy().getPayDescription())
                .PayStatus(pay.getPayStatus().getStepDescription())
                .createdAt(pay.getCreatedAt())
                .build();
    }
}

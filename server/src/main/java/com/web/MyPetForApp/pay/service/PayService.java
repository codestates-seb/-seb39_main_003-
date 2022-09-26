package com.web.MyPetForApp.pay.service;

import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import com.web.MyPetForApp.member.service.MemberService;
import com.web.MyPetForApp.order.entity.Order;
import com.web.MyPetForApp.order.entity.OrderItem;
import com.web.MyPetForApp.order.repository.OrderItemRepository;
import com.web.MyPetForApp.order.repository.OrderRepository;
import com.web.MyPetForApp.pay.entity.Pay;
import com.web.MyPetForApp.pay.repository.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PayService {
    private final PayRepository payRepository;
    private final MemberService memberService;
    private final OrderRepository orderRepository;

    public Pay create(Pay pay,String memberId, Long orderId) {
        Order findOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("주문했던 상품이 존재하지 않습니다.")
        );
        Member findMember = memberService.findVerifiedMember(memberId);
        pay.addOrder(findOrder);
        pay.changeMember(findMember);
        return payRepository.save(pay);
    }

    public Pay read(Long payId) {
        return findVerifiedPay(payId);
    }

    public void delete(Long payId) {
        payRepository.deleteById(payId);
    }

    private Pay findVerifiedPay(Long payId) {
        return payRepository.findById(payId).orElseThrow(
                () -> new IllegalArgumentException("해당 결제 정보가 존재하지 않습니다.")
        );
    }
}

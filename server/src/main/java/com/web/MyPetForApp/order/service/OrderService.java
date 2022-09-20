package com.web.MyPetForApp.order.service;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import com.web.MyPetForApp.order.dto.OrderItemDto;
import com.web.MyPetForApp.order.entity.Order;
import com.web.MyPetForApp.order.entity.OrderItem;
import com.web.MyPetForApp.order.repository.OrderItemRepository;
import com.web.MyPetForApp.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    public Order createOrder(List<OrderItemDto.Post> post, Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        Order order = new Order();
        order.setMember(member);
        order.resetInfo(member);
        for (OrderItemDto.Post orderItemPostDto : post) {
            Item item = itemRepository.findById(orderItemPostDto.getItemId())
                                    .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
            OrderItem orderItem = OrderItem
                    .builder()
                    .orderItemCnt(orderItemPostDto.getOrderItemCnt())
                    .build();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItemRepository.save(orderItem);
        }
        return orderRepository.save(order);
    }

    public Order findOrder(Long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));
    }

    public Page<Order> findOrders(Long memberId, int page, int size){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        return orderRepository.findAllByMember(member, PageRequest.of(page, size,
                Sort.by("orderId").descending()));
    }

    public Order updateOrder(Long orderId, Order.OrderStatus orderStatus){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
        order.changeOrderStatus(orderStatus);
        return order;
    }

    public void cancelOrder(Long orderId){
        Order findOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));
        int step = findOrder.getOrderStatus().getStepNumber();
        if(step >= 2){
            throw new IllegalArgumentException("주문이 확정되기 전에만 취소가 가능합니다.");
        }
        findOrder.changeOrderStatus(Order.OrderStatus.ORDER_CANCEL);
    }

}

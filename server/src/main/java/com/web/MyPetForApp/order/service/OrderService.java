package com.web.MyPetForApp.order.service;

import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
import com.web.MyPetForApp.image.service.ImageService;
import com.web.MyPetForApp.item.dto.ItemDto;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.service.ItemService;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.service.MemberService;
import com.web.MyPetForApp.order.dto.OrderDto;
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
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemService itemService;
    private final MemberService memberService;

    public Order createOrder(OrderDto.Post orderPostDto, String memberId){
        Member member = memberService.findVerifiedMember(memberId);
        List<OrderItemDto.Post> orderItemList = orderPostDto.getOrderItems();
        Order order = new Order();  // Order 생성
        order.changeMember(member); // Order-Member 연관관계 설정

        orderInformation(orderPostDto, member, order); // 주문 정보 설정

        int orderPrice = 0;
        for (OrderItemDto.Post orderItemPostDto : orderItemList) {   // orderItemPostDto List들의 요소들을 순회하며 반복
            Item item = itemService.findVerifiedItem(orderItemPostDto.getItemId()); // 상품 검증
            itemService.checkStockCnt(orderItemPostDto.getOrderItemCnt(), item);
            OrderItem orderItem = OrderItem // OrderItem 생성
                    .builder()
                    .orderItemCnt(orderItemPostDto.getOrderItemCnt())
                    .snapshotItemId(item.getItemId())
                    .snapshotItemName(item.getItemName())
                    .snapshotPrice(item.getPrice())
                    .snapshotImage(item.getThumbnail())
                    .build();
            orderItem.changeOrder(order);  // OrderItem-Order 연관관계 설정
            orderItemRepository.save(orderItem);
        }
        return orderRepository.save(order);

    }

    public Order findOrder(Long orderId){
        return findVerifiedOrder(orderId);
    }

    public Page<Order> findOrders(String memberId, int page, int size){
        Member member = memberService.findVerifiedMember(memberId);
        return orderRepository.findAllByMember(member, PageRequest.of(page, size,
                Sort.by("orderId").descending()));
    }

    public Order updateOrder(Long orderId, String orderStatus){
        Order order = findVerifiedOrder(orderId);
        order.updateOrderStatus(Order.OrderStatus.of(orderStatus));
        return order;
    }

    public void cancelOrder(Long orderId){
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();
        if(step > 2){
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }
        findOrder.updateOrderStatus(Order.OrderStatus.ORDER_CANCEL);
    }

    public Order findVerifiedOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
    }

    public void orderInformation(OrderDto.Post requestBody, Member member, Order order) {
        String newName = requestBody.getNewName();
        String newAddress = requestBody.getNewAddress();
        String newPhone = requestBody.getNewPhone();
        String requirement = requestBody.getRequirement();
        if(requirement != null){
            order.changeRequirement(requirement);
        } else {
            order.resetRequirement();
        }

        if(newName != null && newAddress != null && newPhone != null){
            order.changeNewName(newName);
            order.changeNewAddress(newAddress);
            order.changeNewPhone(newPhone);
        } else {
            order.resetInfo(member);    // Order의 주문 정보(주소, 전화번호 등)들을 Member의 정보로 초기화
        }
    }
}

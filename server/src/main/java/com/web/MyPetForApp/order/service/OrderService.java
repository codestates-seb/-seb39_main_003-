package com.web.MyPetForApp.order.service;

import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
import com.web.MyPetForApp.image.service.ImageService;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.service.ItemService;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.service.MemberService;
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

    private final ImageService imageService;

    public Order createOrder(List<OrderItemDto.Post> post, String memberId){
        Member member = memberService.findVerifiedMember(memberId);
        Order order = new Order();  // Order 생성
        order.changeMember(member); // Order-Member 연관관계 설정
        order.resetInfo(member);    // Order의 주문 정보(주소, 전화번호 등)들을 Member의 정보로 초기화
        int orderPrice = 0;
        for (OrderItemDto.Post orderItemPostDto : post) {   // orderItemPostDto List들의 요소들을 순회하며 반복
            List<String> findImages = imageService.findFilesById("domain",orderItemPostDto.getItemId());
            Item item = itemService.findVerifiedItem(orderItemPostDto.getItemId()); // 상품 검증
            itemService.checkStockCnt(orderItemPostDto.getOrderItemCnt(), item);
            OrderItem orderItem = OrderItem // OrderItem 생성
                    .builder()
                    .orderItemCnt(orderItemPostDto.getOrderItemCnt())
                    .snapshotItemId(item.getItemId())
                    .snapshotItemName(item.getItemName())
                    .snapshotPrice(item.getPrice())
                    .snapshotImage(findImages.get(findImages.size() - 1).substring  // 등록되있는 상품 메인 이미지 저장
                            (findImages.get(findImages.size() - 1).lastIndexOf("/") + 1))
                    .build();
            orderItem.changeOrder(order);  // OrderItem-Order 연관관계 설정
            orderItemRepository.save(orderItem);
        }
        order.updateOrderStatus(Order.OrderStatus.PAY_WAIT);
        return orderRepository.save(order);
        // PayService 호출해서 결제와 주문을 하나의 트랜잭션으로 처리하기 (멘토님께 이렇게 하는게 맞는지 여쭤보기)

        // 혹시 다른부분도 트랜잭션 처리할 것이 있는지??
    }

    public Order findOrder(Long orderId){
        return findVerifiedOrder(orderId);
    }

    public Page<Order> findOrders(String memberId, int page, int size){
        Member member = memberService.findVerifiedMember(memberId);
        return orderRepository.findAllByMember(member, PageRequest.of(page, size,
                Sort.by("orderId").descending()));
    }

    public Order updateOrder(Long orderId, Order.OrderStatus orderStatus){
        Order order = findVerifiedOrder(orderId);
        order.updateOrderStatus(orderStatus);
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

}

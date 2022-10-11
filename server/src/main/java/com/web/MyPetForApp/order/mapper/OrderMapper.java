package com.web.MyPetForApp.order.mapper;

import com.web.MyPetForApp.order.dto.OrderDto;
import com.web.MyPetForApp.order.dto.OrderItemDto;
import com.web.MyPetForApp.order.entity.Order;
import com.web.MyPetForApp.order.entity.OrderItem;
import com.web.MyPetForApp.util.AesEncryption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final AesEncryption aesEncryption;

    public OrderDto.Response orderToOrderResponseDto(Order order) throws Exception {
        return  OrderDto.Response.builder()
                .orderId(order.getOrderId())
                .memberId(order.getMember().getMemberId())
                .newAddress(aesEncryption.doDecrypt(order.getNewAddress()))
                .newName(order.getNewName())
                .newPhone(aesEncryption.doDecrypt(order.getNewPhone()))
                .requirement(order.getRequirement())
                .orderStatus(order.getOrderStatus().getStepDescription())
                .createdAt(order.getCreatedAt())
                .modifiedAt(order.getModifiedAt())
                .orderItems(orderItemsToOrderItemResponseDto(order.getOrderItems()))
                .orderPrice(order.getOrderPrice())
                .build();


    }

    public List<OrderItemDto.Response> orderItemsToOrderItemResponseDto(List<OrderItem> orderItems){
        return orderItems
                .stream()
                .map(orderItem -> OrderItemDto.Response.builder()
                        .orderItemId(orderItem.getOrderItemId())
                        .orderItemCnt(orderItem.getOrderItemCnt())
                        .snapshotPrice(orderItem.getSnapshotPrice())
                        .totalPrice(orderItem.getTotalPrice())
                        .snapshotItemName(orderItem.getSnapshotItemName())
                        .snapshotImage(orderItem.getSnapshotImage())
                        .snapshotItemId(orderItem.getSnapshotItemId())
                        .build())
                .collect(Collectors.toList());
    }

    public List<OrderDto.Response> ordersToOrderResponseDto(List<Order> orders) throws Exception {

        List<OrderDto.Response> responses = orders
                .stream()
                .map(order -> {
                    try {
                        return OrderDto.Response.builder()
                                .orderId(order.getOrderId())
                                .memberId(order.getMember().getMemberId())
                                .newAddress(aesEncryption.doDecrypt(order.getNewAddress()))
                                .newName(order.getNewName())
                                .newPhone(aesEncryption.doDecrypt(order.getNewPhone()))
                                .requirement(order.getRequirement())
                                .orderStatus(order.getOrderStatus().getStepDescription())
                                .createdAt(order.getCreatedAt())
                                .modifiedAt(order.getModifiedAt())
                                .orderItems(orderItemsToOrderItemResponseDto(order.getOrderItems()))
                                .orderPrice(order.getOrderPrice())
                                .build();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
        return responses;
    }
}

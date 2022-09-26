package com.web.MyPetForApp.order.mapper;

import com.web.MyPetForApp.order.dto.OrderDto;
import com.web.MyPetForApp.order.dto.OrderItemDto;
import com.web.MyPetForApp.order.entity.Order;
import com.web.MyPetForApp.order.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public OrderDto.Response orderToOrderResponseDto(Order order){
        int orderPrice = 0;

        OrderDto.Response response = OrderDto.Response.builder()
                .orderId(order.getOrderId())
                .memberId(order.getMember().getMemberId())
                .newAddress(order.getNewAddress())
                .newName(order.getNewName())
                .newPhone(order.getNewPhone())
                .requirement(order.getRequirement())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .modifiedAt(order.getModifiedAt())
                .orderItems(orderItemsToOrderItemResponseDto(order.getOrderItems()))
                .build();
        return response;
    }

    public List<OrderItemDto.Response> orderItemsToOrderItemResponseDto(List<OrderItem> orderItems){
        return orderItems
                .stream()
                .map(orderItem -> OrderItemDto.Response.builder()
                        .orderItemId(orderItem.getOrderItemId())
                        .orderItemCnt(orderItem.getOrderItemCnt())
                        .snapshotPrice(orderItem.getSnapshotPrice())
                        .totalPrice(orderItem.getOrderItemCnt() * orderItem.getSnapshotPrice())
                        .snapshotItemName(orderItem.getSnapshotItemName())
                        .image(orderItem.getSnapshotImage())
                        .snapshotItemId(orderItem.getSnapshotItemId())
                        .build())
                .collect(Collectors.toList());
    }

    public List<OrderDto.Response> ordersToOrderResponseDto(List<Order> orders){
        return orders
                .stream()
                .map(order -> OrderDto.Response.builder()
                        .orderId(order.getOrderId())
                        .memberId(order.getMember().getMemberId())
                        .newAddress(order.getNewAddress())
                        .newName(order.getNewName())
                        .newPhone(order.getNewPhone())
                        .requirement(order.getRequirement())
                        .orderStatus(order.getOrderStatus())
                        .createdAt(order.getCreatedAt())
                        .modifiedAt(order.getModifiedAt())
                        .orderItems(orderItemsToOrderItemResponseDto(order.getOrderItems()))
                        .build())
                .collect(Collectors.toList());
    }
}

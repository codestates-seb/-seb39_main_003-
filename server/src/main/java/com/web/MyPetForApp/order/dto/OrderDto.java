package com.web.MyPetForApp.order.dto;

import com.web.MyPetForApp.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post{
        private String memberId;
        private List<OrderItemDto.Post> orderItems;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch{
        private Order.OrderStatus orderStatus;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long orderId;
        private String memberId;
        private String newAddress;
        private String newPhone;
        private String newName;
        private String requirement;
        private int orderPrice;
        private Order.OrderStatus orderStatus;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<OrderItemDto.Response> orderItems;
    }
}

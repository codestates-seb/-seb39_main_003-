package com.web.MyPetForApp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class OrderItemDto {
    @Getter
    public static class Post{
        private Long itemId;
        private int orderItemCnt;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private int orderItemCnt;
        private int price;
        private int totalPrice;
        private String itemName;
        private String image;
        private Long itemId;
    }
}

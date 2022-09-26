package com.web.MyPetForApp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class OrderItemDto {
    @Getter
    public static class Post{
        private String itemId;
        private int orderItemCnt;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long orderItemId;
        private int orderItemCnt;
        private int snapshotPrice;
        private int totalPrice;
        private String snapshotItemName;
        private String image;
        private String snapshotItemId;
    }
}

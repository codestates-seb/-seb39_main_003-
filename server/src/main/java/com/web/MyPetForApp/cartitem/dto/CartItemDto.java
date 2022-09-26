package com.web.MyPetForApp.cartitem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CartItemDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post{
        private int itemCnt;
        private String itemId;
        private String memberId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch{
        private int itemCnt;
        private String itemId;
        private Long memberId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long cartItemId;
        private int itemCnt;
        private int price;
        private int totalPrice;
        private String itemName;
        private String image;
        private String itemId;
    }
}

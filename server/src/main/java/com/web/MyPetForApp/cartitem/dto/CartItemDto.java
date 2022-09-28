package com.web.MyPetForApp.cartitem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CartItemDto {
    @Schema(name = "ItemPost", description = "장바구니 Post 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Schema(description = "담을 상품 갯수", example = "1")
        private int itemCnt;
        @Schema(description = "상품 식별 번호", example = "000001")
        private String itemId;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
    }
    @Schema(name = "ItemPost", description = "장바구니 Patch 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Patch{
        @Schema(description = "담을 상품 갯수", example = "1")
        private int itemCnt;
        @Schema(description = "상품 식별 번호", example = "000001")
        private String itemId;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
    }
    @Schema(name = "ItemPost", description = "장바구니 데이터 반환 모델")
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{
        @Schema(description = "장바구니 식별 번호", example = "000001")
        private Long cartItemId;
        @Schema(description = "담긴 상품 갯수", example = "1")
        private int itemCnt;
        @Schema(description = "상품 가격", example = "5000")
        private int price;
        @Schema(description = "총 가격", example = "5000")
        private int totalPrice;
        @Schema(description = "상품 이름", example = "5000")
        private String itemName;
        @Schema(description = "상품 이미지", example = "5000")
        private String image;
        @Schema(description = "상품 식별 번호", example = "000001")
        private String itemId;
    }
}

package com.web.MyPetForApp.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class OrderItemDto {
    @Schema(name = "OrderItemPost", description = "한 주문에 포함될 상 Post 요청 모델")
    @Getter
    public static class Post{
        @Schema(description = "상품 식별 번호", example = "000001")
        private String itemId;
        private int orderItemCnt;
    }
    @Schema(name = "OrderItemResponse", description = "주문-상품 데이터 반환 모델")
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{
        @Schema(description = "주문-상품 식별 번호", example = "1")
        private Long orderItemId;
        @Schema(description = "주문할 갯수", example = "2")
        private int orderItemCnt;
        @Schema(description = "주문할 상품 가격", example = "5000")
        private int snapshotPrice;
        @Schema(description = "주문한 상품 중 해당 상품의 주문 가격 합계", example = "10000")
        private int totalPrice;
        @Schema(description = "주문-상품 이름", example = "진공 청소기")
        private String snapshotItemName;
        @Schema(description = "주문한 상품의 썸네일 이미지")
        private String snapshotImage;
        @Schema(description = "주문한 상품의 식별번호")
        private String snapshotItemId;
    }
}

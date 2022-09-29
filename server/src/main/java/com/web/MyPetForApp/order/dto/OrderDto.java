package com.web.MyPetForApp.order.dto;

import com.web.MyPetForApp.order.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    @Schema(name = "OrderPost", description = "주문 Post 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
        @Schema(description = "한 주문에 포함될 상품 리스트 Dto")
        private List<OrderItemDto.Post> orderItems;
    }
    @Schema(name = "OrderPatch", description = "주문 Patch 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Patch{
        @Schema(description = "주문 상태")
        private Order.OrderStatus orderStatus;
    }

    @Schema(name = "OrderResponse", description = "주문 데이터 반환 모델")
    @Getter
    @Builder
    @AllArgsConstructor
    @Setter
    public static class Response{
        @Schema(description = "주문 식별 번호", example = "1")
        private Long orderId;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
        @Schema(description = "새 주소", example = "서울 중곡동 225-10")
        private String newAddress;
        @Schema(description = "새 휴대폰번호", example = "010-2060-1122")
        private String newPhone;
        @Schema(description = "새 이름", example = "홍길동")
        private String newName;
        @Schema(description = "주문 시 요청사항", example = "안전하게 와주세요")
        private String requirement;
        @Schema(description = "총 주문금액", example = "80000")
        private int orderPrice;
        @Schema(description = "주문 상태")
        private Order.OrderStatus orderStatus;
        @Schema(description = "생성일자", example = "2022-09-28 22:26:56")
        private LocalDateTime createdAt;
        @Schema(description = "마지막 수정 일자", example = "2022-09-28 22:26:56")
        private LocalDateTime modifiedAt;
        @Schema(description = "한 주문에 포함될 상품 리스트 Dto")
        private List<OrderItemDto.Response> orderItems;
    }
}

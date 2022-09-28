package com.web.MyPetForApp.pay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class PayDto {
    @Schema(name = "PayPost", description = "결제 Post 요청 모델")
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Post {
        @Schema(description = "총 결제금액", example = "50000")
        private int amount;
        @Schema(description = "결제 수단", example = "계좌이체")
        private String payBy;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
        @Schema(description = "주문 식별 번호", example = "1")
        private Long orderId;
    }

    @Schema(name = "PayPatch", description = "결제 Patch 요청 모델")
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Patch {
        @Schema(description = "결제 식별 번호", example = "1")
        private Long PayId;
        @Schema(description = "총 결제금액", example = "50000")
        private int amount;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
        @Schema(description = "결제 수단", example = "계좌이체")
        private String payBy;
        @Schema(description = "결제 상태", example = "결제 완료")
        private String PayStatus;
    }

    @Schema(name = "PayResponse", description = "결제 데이터 반환 모델")
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Response {
        @Schema(description = "결제 식별 번호", example = "1")
        private Long PayId;
        @Schema(description = "총 결제금액", example = "50000")
        private int amount;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
        @Schema(description = "결제 수단", example = "계좌이체")
        private String payBy;
        @Schema(description = "결제 상태", example = "결제 완료")
        private String PayStatus;
        @Schema(description = "결제일자", example = "2022-09-28 22:26:56")
        private LocalDateTime createdAt;
    }
}

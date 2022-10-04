package com.web.MyPetForApp.pay.dto;

import com.web.MyPetForApp.order.dto.OrderItemDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PayDto {
    @Schema(name = "PayPost", description = "결제 Post 요청 모델")
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Post {
        @Schema(description = "총 결제금액", example = "50000")
        @Min(100)
        private int amount;

        @Schema(description = "결제 수단", example = "계좌이체")
        @NotBlank
        private String payBy;

        @Schema(description = "회원 식별 번호", example = "000001")
        @NotBlank
        private String memberId;

        @Schema(description = "한 주문에 포함될 상품 리스트 Dto")
        @NotNull
        private List<OrderItemDto.Post> orderItems;

        @Schema(description = "새 주소", example = "서울 중곡동 225-10", nullable = true)
        @Pattern(regexp = "^[가-힣\\d\\s-]+$")
        private String newAddress;

        @Schema(description = "새 휴대폰번호", example = "010-2060-1122", nullable = true)
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 10~11자의 숫자와 '-'로 구성되어야 합니다")
        private String newPhone;

        @Schema(description = "새 이름", example = "홍길동", nullable = true)
        @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글이어야 합니다.")
        private String newName;

        @Schema(description = "주문 시 요청사항", example = "안전하게 와주세요", nullable = true)
        private String requirement;
    }

//    @Schema(name = "PayPatch", description = "결제 Patch 요청 모델")
//    @AllArgsConstructor
//    @Getter
//    @Builder
//    public static class Patch {
//        @Schema(description = "결제 식별 번호", example = "1")
//        @Positive
//        private Long PayId;
//        @Schema(description = "총 결제금액", example = "50000")
//        @Min(100)
//        private int amount;
//        @Schema(description = "회원 식별 번호", example = "000001")
//        @NotBlank
//        private String memberId;
//        @Schema(description = "결제 수단", example = "계좌이체")
//        @NotBlank
//        private String payBy;
//        @Schema(description = "결제 상태", example = "결제 완료")
//        private String PayStatus;
//    }

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
        @Schema(description = "주문 식별 번호", example = "1")
        private Long orderId;
    }
}

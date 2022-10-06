package com.web.MyPetForApp.item.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class ItemDto {
    @Schema(name = "ItemPost", description = "상품 Post 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Schema(description = "회원 식별 번호", example = "000001")
        @NotBlank
        private String memberId;

        @Schema(description = "상품 이름", example = "다이슨 청소기")
        @NotBlank(message = "상품명은 공백이 아니어야 합니다.")
        @Size(min = 5, max = 50)
        private String itemName;

        @Schema(description = "상품 가격", example = "80000")
        @NotNull
        @Min(value = 100, message = "가격은 100원 이상이여야 합니다.")
        @Positive
        private Integer price;

        @Schema(description = "상품 재고량", example = "100")
        @NotNull
        @PositiveOrZero
        private Integer stockCnt;

        @Schema(description = "상품 상세정보", example = "안녕하세요....")
        @Size(min = 5, max = 500)
        private String info;

        @Schema(description = "상품 카테고리 식별번호", example = "1")
        @NotBlank
        private String itemCategoryId;
    }

    @Schema(name = "ItemPatch", description = "상품 Patch 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Patch{
        @Schema(description = "회원 식별 번호", example = "000001")
        @NotBlank
        private String memberId;

        @Schema(description = "상품 이름", example = "다이슨 청소기")
        @Size(min = 5, max = 50)
        private String itemName;

        @Schema(description = "상품 가격", example = "80000")
        @Min(value = 100, message = "가격은 100원 이상이여야 합니다.")
        private Integer price;

        @Schema(description = "상품 재고량", example = "100")
        @PositiveOrZero
        private Integer stockCnt;

        @Schema(description = "상품 상세정보", example = "안녕하세요....")
        @Size(min = 5, max = 500)
        private String info;
    }

    @Schema(name = "ItemResponse", description = "상품 데이터 반환 모델")
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{
        @Schema(description = "상품 식별 번호", example = "000001")
        private String itemId;
        @Schema(description = "상품 이름", example = "다이슨 청소기")
        private String itemName;
        @Schema(description = "상품 가격", example = "80000")
        private int price;
        @Schema(description = "상품 판매량", example = "10")
        private int soldCnt;
        @Schema(description = "상품 재고량", example = "100")
        private int stockCnt;
        @Schema(description = "상품 상세정보", example = "안녕하세요....")
        private String info;
        @Schema(description = "상품 좋아요 수", example = "5")
        private int wishCnt;
        @Schema(description = "상품 카테고리", example = "사료")
        private String itemCategory;
        @Schema(description = "회원")
        private String nickName;
        @Schema(description = "상품 썸네일 이미지 파일 이름")
        private String thumbnail;
    }
}
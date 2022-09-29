package com.web.MyPetForApp.item.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ItemDto {
    @Schema(name = "ItemPost", description = "상품 Post 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
        @Schema(description = "상품 이름", example = "다이슨 청소기")
        private String itemName;
        @Schema(description = "상품 가격", example = "80000")
        private int price;
        @Schema(description = "상품 재고량", example = "100")
        private int stockCnt;
        @Schema(description = "상품 상세정보", example = "안녕하세요....")
        private String info;
        @Schema(description = "상품 카테고리 식별번호", example = "1")
        private String itemCategoryId;
    }

    @Schema(name = "ItemPatch", description = "상품 Patch 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Patch{
        @Schema(description = "회원 식별 번호", example = "000001")
        private Long memberId;
        @Schema(description = "상품 이름", example = "다이슨 청소기")
        private String itemName;
        @Schema(description = "상품 가격", example = "80000")
        private int price;
        @Schema(description = "상품 재고량", example = "100")
        private int stockCnt;
        @Schema(description = "상품 상세정보", example = "안녕하세요....")
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
        private String member;
        @Schema(description = "상품 이미지 파일 이름 리스트")
        private List<String> fileNameList;
        @Schema(description = "상품 썸네일 이미지 파일 이름")
        private String thumbnail;
    }
}
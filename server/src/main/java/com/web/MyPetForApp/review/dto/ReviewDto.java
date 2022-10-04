package com.web.MyPetForApp.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewDto {
    @Schema(name = "ReviewPost", description = "상품 리뷰 Post 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Schema(description = "별점 갯수", example = "4")
        private int starCnt;
        @Schema(description = "상품 이미지")
        private String photo;
        @Schema(description = "리뷰 내용", example = "리뷰내용 1나")
        private String reviewContent;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
        @Schema(description = "상품 식별 번호", example = "000001")
        private String itemId;
    }
    @Schema(name = "ReviewPatch", description = "상품 리뷰 Patch 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Patch{
        @Schema(description = "별점 갯수", example = "4")
        private int starCnt;
        @Schema(description = "상품 이미지")
        private String photo;
        @Schema(description = "리뷰 내용", example = "리뷰내용 1나")
        private String reviewContent;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
    }
   @Schema(name = "ReviewResponse", description = "상품 리뷰 데이터 반환 모델")
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{
        @Schema(description = "회원 식별 번호", example = "1")
        private Long reviewId;
        @Schema(description = "리뷰 내용", example = "리뷰내용 1나")
        private String reviewContent;
        @Schema(description = "별점 갯수", example = "4")
        private int startCnt;
        @Schema(description = "생성일자", example = "2022-09-28 22:26:56")
        private LocalDateTime createAt;
        @Schema(description = "마지막 수정 일자", example = "2022-09-28 22:26:56")
        private LocalDateTime modifiedAt;
        @Schema(description = "상품 식별 번호", example = "000001")
        private String itemId;
        @Schema(description = "회원 이름", example = "홍길동")
        private String nickName;
    }
}

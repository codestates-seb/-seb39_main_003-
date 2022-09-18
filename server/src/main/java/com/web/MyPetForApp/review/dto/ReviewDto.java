package com.web.MyPetForApp.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post{
        private int starCnt;
        private String photo;
        private String reviewContent;
        private Long memberId;
        private Long itemId;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{
        private int starCnt;
        private String photo;
        private String reviewContent;
        private Long memberId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long reviewId;
        private String reviewContent;
        private int startCnt;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;
        private Long itemId;
        private String memberName; // 이름? 닉네임?
    }

}

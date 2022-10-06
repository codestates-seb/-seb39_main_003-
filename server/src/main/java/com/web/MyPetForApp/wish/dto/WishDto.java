package com.web.MyPetForApp.wish.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class WishDto {

    @Schema(name = "WishPost", description = "좋아요 Post 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Schema(description = "상품 식별 번호", example = "000001")
        @NotBlank
        private String itemId;
        @Schema(description = "회원 식별번호", example = "000001")
        @NotBlank
        private String memberId;
    }
    @Schema(name = "WishResponse", description = "좋아요 데이터 반환 모델")
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{
        @Schema(description = "좋아요 수", example = "15")
        private int wishCnt;
        @Schema(description = "좋아요 여부", example = "true")
        private boolean isWished;
    }
}

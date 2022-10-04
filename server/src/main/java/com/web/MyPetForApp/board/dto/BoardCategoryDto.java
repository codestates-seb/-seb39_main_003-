package com.web.MyPetForApp.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BoardCategoryDto {
    @Schema(name = "BoardCategoryResponse", description = "게시판 종류별 카테고리 리스트 응답 모델")
    @Builder
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "게시글 카테고리 식별번호", example = "11")
        private Long boardCategoryId;
        @Schema(description = "게시글 카테고리 이름", example = "커뮤니티")
        private String boardCategoryName;
        @Schema(description = "게시글 상위 카테고리 식별번호", example = "1")
        private Integer pid;
    }
}

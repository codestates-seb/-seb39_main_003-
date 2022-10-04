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
        private Long boardCategoryId;
        private String boardCategoryName;
        private Integer pid;
    }
}

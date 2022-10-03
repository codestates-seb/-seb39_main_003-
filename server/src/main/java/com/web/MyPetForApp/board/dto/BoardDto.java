package com.web.MyPetForApp.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {
    @Schema(name = "BoardPost", description = "게시판 Post 요청 모델")
    @Getter
    public static class Post{
        @Schema(description = "게시글 제목", example = "제목1")
        private String title;
        @Schema(description = "게시글 내용", example = "내용1")
        private String boardContents;
        @Schema(description = "작성 회원 식별번호", example = "000001")
        private String memberId;
        @Schema(description = "작성할 게시판의 태그(종류)", example = "11")
        private List<Long> tagIds;
    }
    @Schema(name = "BoardPatch", description = "게시판 Patch 요청 모델")
    @Getter
    public static class Patch{
        @Schema(description = "게시글 제목", example = "제목1")
        private String title;
        @Schema(description = "게시글 내용", example = "내용1")
        private String boardContents;
        @Schema(description = "작성할 게시판의 태그(종류)", example = "11")
        private List<Long> tagIds;
    }
    @Schema(name = "BoardDetail", description = "게시판 상세 페이지 데이터 반환 모델")
    @Builder
    @Setter
    @Getter
    public static class Detail{
        @Schema(description = "게시글 식별번호", example = "1")
        private Long boardId;
        @Schema(description = "게시글 제목", example = "제목1")
        private String title;
        @Schema(description = "게시글 내용", example = "내용1")
        private String boardContents;
        @Schema(description = "조회 수", example = "15")
        private int view;
        @Schema(description = "회원 별명", example = "별명1")
        private String nickName;
        @Schema(description = "생성일자", example = "2022-09-28 22:26:56")
        private LocalDateTime createdAt;
        @Schema(description = "마지막 수정 일자", example = "2022-09-28 22:26:56")
        private LocalDateTime modifiedAt;
    }
    @Schema(name = "BoardResponse", description = "게시판 Post 요청 모델")
    @Builder
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "게시글 식별번호", example = "1")
        private Long boardId;
        @Schema(description = "게시글 제목", example = "제목1")
        private String title;
        @Schema(description = "조회 수", example = "15")
        private int view;
        @Schema(description = "회원 별명", example = "별명1")
        private String nickName;
        @Schema(description = "마지막 수정 일자", example = "2022-09-28 22:26:56")
        private LocalDateTime modifiedAt;
    }
}

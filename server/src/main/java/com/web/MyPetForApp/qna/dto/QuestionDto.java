package com.web.MyPetForApp.qna.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class QuestionDto {
    @Schema(name = "QnAPost", description = "QnA Post 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Schema(description = "QnA 제목", example = "질문 1나")
        @NotBlank
        @Size(min = 5, max = 50)
        private String questionTitle;
        @Schema(description = "QnA 내용", example = "내용 1나")
        @NotBlank
        @Size(min = 5, max = 500)
        private String questionContent;
        @Schema(description = "회원 식별 번호", example = "000001")
        @NotBlank
        private String memberId;
        @Schema(description = "상품 식별 번호", example = "000001")
        @NotBlank
        private String itemId;
    }
    @Schema(name = "QnAPatch", description = "QnA Patch 요청 모델")
    @Getter
    @AllArgsConstructor
    public static class Patch{
        @Schema(description = "QnA 제목", example = "질문 1나")
        @Size(min = 5, max = 50)
        private String questionTitle;
        @Schema(description = "QnA 내용", example = "내용 1나")
        @Size(min = 5, max = 500)
        private String questionContent;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
    }
    @Schema(name = "QnAResponse", description = "QnA 데이터 반환 모델")
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{
        @Schema(description = "QnA 식별 번호", example = "1")
        private Long questionId;
        @Schema(description = "채택 여부", example = "false")
        private boolean checked;
        @Schema(description = "QnA 제목", example = "질문 1나")
        private String questionTitle;
        @Schema(description = "QnA 내용", example = "내용 1나")
        private String questionContent;
        @Schema(description = "생성일자", example = "2022-09-28 22:26:56")
        private LocalDateTime createAt;
        @Schema(description = "마지막 수정 일자", example = "2022-09-28 22:26:56")
        private LocalDateTime modifiedAt;
        @Schema(description = "상품 식별 번호", example = "000001")
        private String itemId;
        @Schema(description = "회원 별명", example = "홍길동")
        private String nickName;
    }

}

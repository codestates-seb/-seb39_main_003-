package com.web.MyPetForApp.qna.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class AnswerDto {
    @Schema(name = "AnswerPost", description = "QnA 답변 Post 요청 모델")
    @AllArgsConstructor
    @Getter
    public static class Post{
        @Schema(description = "QnA 식별 번호", example = "1")
        private Long questionId;
        @Schema(description = "QnA 답변 내용", example = "내용 1나")
        private String answerContent;
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
    }
    @Schema(name = "AnswerResponse", description = "Answer 데이터 반환 모델")
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Response{
        @Schema(description = "QnA 답변 식별 번호", example = "1")
        private Long answerId;
        @Schema(description = "QnA 식별 번호", example = "1")
        private Long questionId;
        @Schema(description = "QnA 답변 내용", example = "내용 1나")
        private String answerContent;
        @Schema(description = "회원 별명", example = "홍길동")
        private String nickName;
        @Schema(description = "마지막 수정 일자", example = "2022-09-28 22:26:56")
        private LocalDateTime modifiedAt;
    }
}

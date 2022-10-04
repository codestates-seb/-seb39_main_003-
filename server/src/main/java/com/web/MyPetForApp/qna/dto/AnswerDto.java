package com.web.MyPetForApp.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class AnswerDto {
    @AllArgsConstructor
    @Getter
    public static class Post{
        private Long questionId;
        private String answerContent;
        private String memberId;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Response{
        private Long answerId;
        private Long questionId;
        private String answerConetent;
        private String nickName;
        private LocalDateTime modifiedAt;
    }
}

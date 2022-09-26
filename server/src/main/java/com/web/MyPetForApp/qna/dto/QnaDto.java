package com.web.MyPetForApp.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class QnaDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post{
        private String qnaTitle;
        private String qnaContent;
        private String memberId;
        private String itemId;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{
        private String qnaTitle;
        private String qnaContent;
        private String memberId;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long qnaId;
        private boolean checked;
        private String qnaTitle;
        private String qnaContent;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;
        private String itemId;
        private String memberName; // 이름? 닉네임?
    }

}

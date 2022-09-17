package com.web.MyPetForApp.qna.dto;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class QnaDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post{
        private String qnaTitle;
        private String qnaContent;
        private Long memberId;
        private Long itemId;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{
        private String qnaTitle;
        private String qnaContent;
        private Long memberId;
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
        private Long itemId;
        private String memberName; // 이름? 닉네임?
    }

}

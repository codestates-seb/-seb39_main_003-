package com.web.MyPetForApp.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentDto {
    @Getter
    public static class Post{
        private Long boardId;
        private String memberId;
        private String commentContent;
    }

    @Getter
    public static class Patch{
        private String commentContent;
    }
    @Getter
    @Setter
    @Builder
    public static class Response{
        private Long boardId;
        private String nickName;
        private String commentContent;
        private LocalDateTime modifiedAt;
    }
}

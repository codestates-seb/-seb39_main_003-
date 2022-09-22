package com.web.MyPetForApp.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class CommentDto {
    @Getter
    public static class Post{
        private Long boardId;
        private Long memberId;
        private String commentContent;
    }

    @Getter
    public static class Patch{
        private String commentContent;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{
        private String nickName;
        private String commentContent;
    }
}

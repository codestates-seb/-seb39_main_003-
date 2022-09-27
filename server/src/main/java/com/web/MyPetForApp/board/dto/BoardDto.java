package com.web.MyPetForApp.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {
    @Getter
    public static class Post{
        private String title;
        private String boardContents;
        private String memberId;
        private List<Long> tagIds;
    }

    @Getter
    public static class Patch{
        private String title;
        private String boardContents;
        private List<Long> tagIds;
    }
    @Builder
    @Setter
    @Getter
    public static class Detail{
        private Long boardId;
        private String title;
        private String boardContents;
        private int view;
        private String nickName;
        private List<String> tagNames;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Builder
    @Getter
    @Setter
    public static class Response{
        private Long boardId;
        private String title;
        private int view;
        private String nickName;
        private LocalDateTime modifiedAt;
    }
}

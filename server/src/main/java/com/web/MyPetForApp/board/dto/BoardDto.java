package com.web.MyPetForApp.board.dto;

import lombok.Getter;

import java.util.List;

public class BoardDto {
    @Getter
    public static class Post{
        private String title;
        private String boardContents;
        private Long memberId;
        private List<Long> tagIds;
    }
}

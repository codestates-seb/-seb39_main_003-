package com.web.MyPetForApp.board.dto;

import lombok.Builder;
import lombok.Getter;

public class TagDto {
    @Builder
    @Getter
    public static class Response{
        private Long tagId;
        private String tagName;
    }
}

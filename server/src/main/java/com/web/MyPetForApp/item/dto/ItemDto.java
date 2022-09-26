package com.web.MyPetForApp.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ItemDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post{
        private String memberId;
        private String itemName;
        private int price;
        private int stockCnt;
        private String info;
        private Long itemCategoryId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{
        private Long memberId;
        private List<String> itemImages;
        private String itemName;
        private int price;
        private int stockCnt;
        private String info;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private String itemId;
        private String itemName;
        private int price;
        private int soldCnt;
        private int stockCnt;
        private String info;
        private int wishCnt;
        private String itemCategory;
        private String member;
        private List<String> fileNameList;
        private String thumbNail;
    }
}

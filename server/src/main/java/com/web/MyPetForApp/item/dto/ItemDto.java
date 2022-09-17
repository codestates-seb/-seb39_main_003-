package com.web.MyPetForApp.item.dto;

import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.item.entity.ItemCategory;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.order.entity.OrderItem;
import com.web.MyPetForApp.qna.entity.Qna;
import com.web.MyPetForApp.review.entity.Review;
import com.web.MyPetForApp.wish.entity.Wish;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ItemDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post{
        private Long memberId;
        private String image;
        private String itemName;
        private int price;
        private int stockCnt;
        private String info;
        private Long itemCategoryId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{
        private Long memberId;
        private String image;
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
        private Long itemId;
        private String image;
        private String itemName;
        private int price;
        private int soldCnt;
        private int stockCnt;
        private String info;
        private int wishCnt;
        private String itemCategory;
        private String member;
    }
}

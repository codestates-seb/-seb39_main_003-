package com.web.MyPetForApp.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class PayDto {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Post {
        private int amount;
        private String payBy;
        private String memberId;
        private Long orderId;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Patch {
        private Long PayId;
        private int amount;
        private String memberId;
        private String payBy;
        private String PayStatus;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Response {
        private Long PayId;
        private int amount;
        private String memberId;
        private String payBy;
        private String PayStatus;
        private LocalDateTime createdAt;
    }
}

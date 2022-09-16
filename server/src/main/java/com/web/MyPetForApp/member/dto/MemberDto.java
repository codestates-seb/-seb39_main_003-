package com.web.MyPetForApp.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long memberId;

        private String memberName;

        private String nickName;

        private String email;

        private String password;

        private String address;

        private String phone;

        private String profileImg;

        private String roles;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Post {
        private String memberName;

        private String nickName;

        private String email;

        private String password;

        private String address;

        private String phone;

        private String profileImg;

        private String roles;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Patch {
        private Long memberId;

        private String nickName;

        private String password;

        private String address;

        private String phone;

        private String profileImg;
    }
}

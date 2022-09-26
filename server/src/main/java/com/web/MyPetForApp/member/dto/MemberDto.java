package com.web.MyPetForApp.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String memberId;

        private String memberName;

        private String nickName;

        private String email;

        private String password;

        private String address;

        private String phone;

        private String roles;

        private String profileImg;
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

        private String roles;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Patch {
        private String memberId;

        private String nickName;

        private String password;

        private String address;

        private String phone;
    }
}

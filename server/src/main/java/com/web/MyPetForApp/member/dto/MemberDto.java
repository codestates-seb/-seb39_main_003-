package com.web.MyPetForApp.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberDto {

    @Schema(name = "MemberResponse", description = "회원 데이터 반환 모델")
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
        @Schema(description = "회원 실명", example = "홍길동")
        private String memberName;
        @Schema(description = "회원 별명", example = "파이팅")
        private String nickName;
        @Schema(description = "회원 이메일", example = "abc1@abc.com")
        private String email;
        @Schema(description = "회원 비밀번호", example = "1234567")
        private String password;
        @Schema(description = "회원 주소", example = "서울 광진구 아차산로 1길 111-222")
        private String address;
        @Schema(description = "회원 휴대폰번호", example = "010-1111-1111")
        private String phone;
        @Schema(description = "회원 권한", example = "일반 사용자")
        private String roles;
        @Schema(description = "회원 프로필 이미지", example = "profile_1.jpeg")
        private String profileImg;
    }

    @Schema(name = "MemberPost", description = "회원 Post 요청 모델")
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post {
        @Schema(description = "회원 실명", example = "홍길동")
        private String memberName;
        @Schema(description = "회원 별명", example = "파이팅")
        private String nickName;
        @Schema(description = "회원 이메일", example = "abc1@abc.com")
        private String email;
        @Schema(description = "회원 비밀번호", example = "1234567")
        private String password;
        @Schema(description = "회원 주소", example = "서울 광진구 아차산로 1길 111-222")
        private String address;
        @Schema(description = "회원 휴대폰번호", example = "010-1111-1111")
        private String phone;
        @Schema(description = "회원 권한", example = "일반 사용자")
        private String roles;
    }

    @Schema(name = "MemberPatch", description = "회원 Patch 요청 모델")
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Patch {
        @Schema(description = "회원 식별 번호", example = "000001")
        private String memberId;
        @Schema(description = "회원 별명", example = "파이팅")
        private String nickName;
        @Schema(description = "회원 비밀번호", example = "1234567")
        private String password;
        @Schema(description = "회원 주소", example = "서울 광진구 아차산로 1길 111-222")
        private String address;
        @Schema(description = "회원 휴대폰번호", example = "010-1111-1111")
        private String phone;
        @Schema(description = "회원 프로필 이미지", example = "S3베이스Url + 000001/profile_1.jpeg")
        private String profileImg;
    }
}

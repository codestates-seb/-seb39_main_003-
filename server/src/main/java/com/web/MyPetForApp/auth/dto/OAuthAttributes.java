package com.web.MyPetForApp.auth.dto;

import com.web.MyPetForApp.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String memberName;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String memberName, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.memberName = memberName;
        this.email = email;
        this.picture = picture;
    }

    // of 메서드 사용 이유 : OAuth2User에서 반환하는 정보 : Map이며, 이용하는 소셜마다 프로퍼티값이 다르기에 일일이 넣어줘야함.
    public static OAuthAttributes of(String registrationId, String nameAttributeKey, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver(nameAttributeKey, attributes);
        }
        if("kakao".equals(registrationId)) {
            return ofKakao(nameAttributeKey, attributes);
        }
        return ofGoogle(nameAttributeKey, attributes);
    }

    private static OAuthAttributes ofGoogle(String nameAttributeKey, Map<String, Object> attributes) {
        HashMap<String, Object> hashAttributes = new HashMap<>();
        String memberName = (String) attributes.get("name");
        String email = (String) attributes.get("email");
        String profileImg = (String) attributes.get("picture");
        hashAttributes.put("profileImg", profileImg);
        hashAttributes.put("memberName", memberName);
        hashAttributes.put("email", email);
        hashAttributes.put("sub",nameAttributeKey);

        return OAuthAttributes.builder()
                .memberName(memberName)
                .email(email)
                .picture(profileImg)
                .attributes(hashAttributes)
                .nameAttributeKey(nameAttributeKey)
                .build();
    }

    private static OAuthAttributes ofNaver(String nameAttributeKey, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        String profileImg = (String) response.get("profile_image");
        String memberName = (String) response.get("name");
        String email = (String) response.get("email");
        HashMap<String, Object> hashAttributes = new HashMap<>();
        hashAttributes.put("profileImg", profileImg);
        hashAttributes.put("memberName", memberName);
        hashAttributes.put("email", email);
        hashAttributes.put("response",nameAttributeKey);

        return OAuthAttributes.builder()
                .memberName(memberName)
                .email(email)
                .picture(profileImg)
                .attributes(hashAttributes)
                .nameAttributeKey(nameAttributeKey)
                .build();
    }

    private static OAuthAttributes ofKakao(String nameAttributeKey, Map<String, Object> attributes) {
        // kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
        String memberName = (String) kakaoProfile.get("nickname");
        String email = (String) kakaoAccount.get("email");
        String profileImg = (String) kakaoProfile.get("profile_image_url");
        Map<String, Object> hashAttributes = new HashMap<>();
        hashAttributes.put("memberName", memberName);
        hashAttributes.put("email", email);
        hashAttributes.put("profileImg", profileImg);
        hashAttributes.put("id", nameAttributeKey);

        return OAuthAttributes.builder()
                .memberName(memberName)
                .email(email)
                .picture(profileImg)
                .attributes(hashAttributes)
                .nameAttributeKey(nameAttributeKey)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .memberName(memberName)
                .email(email)
                .memberRole(Member.MemberRole.ROLE_USER)
                .profileImg(picture)
                .build();
    }
}

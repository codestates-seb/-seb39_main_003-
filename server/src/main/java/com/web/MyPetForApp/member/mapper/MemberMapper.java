package com.web.MyPetForApp.member.mapper;

import com.web.MyPetForApp.member.dto.MemberDto;
import com.web.MyPetForApp.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member memberPostDtoToMember(MemberDto.Post post) {
        return Member.builder()
                .memberName(post.getMemberName())
                .nickName(post.getNickName())
                .address(post.getAddress())
                .email(post.getEmail())
                .password(post.getPassword())
                .phone(post.getPhone())
                .profileImg(post.getProfileImg())
                .roles(post.getRoles())
                .build();
    }

    public Member memberPatchToMember(MemberDto.Patch patch) {
        return Member.builder()
                .memberId(patch.getMemberId())
                .nickName(patch.getNickName())
                .profileImg(patch.getProfileImg())
                .phone(patch.getPhone())
                .password(patch.getPassword())
                .address(patch.getAddress())
                .build();
    }

    public MemberDto.Response memberToResponse(Member member) {
        return MemberDto.Response.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .address(member.getAddress())
                .nickName(member.getNickName())
                .email(member.getEmail())
                .roles(member.getRoles())
                .password(member.getPassword())
                .phone(member.getPhone())
                .profileImg(member.getProfileImg())
                .build();
    }
}

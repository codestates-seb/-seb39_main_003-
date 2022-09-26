package com.web.MyPetForApp.member.mapper;

import com.web.MyPetForApp.member.dto.MemberDto;
import com.web.MyPetForApp.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member memberPostDtoToMember(MemberDto.Post post, String memberId) {
        return Member.builder()
                .memberName(post.getMemberName())
                .nickName(post.getNickName())
                .address(post.getAddress())
                .email(post.getEmail())
                .password(bCryptPasswordEncoder.encode(post.getPassword()))
                .phone(post.getPhone())
                .memberId(memberId)
                .memberRole(Member.MemberRole.ROLE_USER)
                .build();
    }

    public Member memberPatchToMember(MemberDto.Patch patch) {
        return Member.builder()
                .memberId(patch.getMemberId())
                .nickName(patch.getNickName())
                .phone(patch.getPhone())
                .password(bCryptPasswordEncoder.encode(patch.getPassword()))
                .address(patch.getAddress())
                .build();
    }

    public MemberDto.Response memberToResponse(Member member, String profileImg) {
        return MemberDto.Response.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .address(member.getAddress())
                .nickName(member.getNickName())
                .email(member.getEmail())
                .roles(member.getMemberRole().getRole())
                .password(member.getPassword())
                .phone(member.getPhone())
                .profileImg(profileImg)
                .build();
    }
}

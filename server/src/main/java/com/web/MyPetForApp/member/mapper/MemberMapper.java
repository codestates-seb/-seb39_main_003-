package com.web.MyPetForApp.member.mapper;

import com.web.MyPetForApp.member.dto.MemberDto;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.util.AesEncryption;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AesEncryption aesEncryption;

    public Member memberPostDtoToMember(MemberDto.Post post, String memberId) throws Exception {
        return Member.builder()
                .memberName(post.getMemberName())
                .nickName(post.getNickName())
                .address(aesEncryption.doEncrypt(post.getAddress()))
                .email(post.getEmail())
                .password(bCryptPasswordEncoder.encode(post.getPassword()))
                .phone(aesEncryption.doEncrypt(post.getPhone()))
                .memberId(memberId)
                .memberRole(Member.MemberRole.ROLE_USER)
                .build();
    }

    public Member memberPatchToMember(MemberDto.Patch patch) throws Exception {
        return Member.builder()
                .memberId(patch.getMemberId())
                .nickName(patch.getNickName())
                .phone(aesEncryption.doEncrypt(patch.getPhone()))
                .password(bCryptPasswordEncoder.encode(patch.getPassword()))
                .address(aesEncryption.doEncrypt(patch.getAddress()))
                .build();
    }

    public MemberDto.Response memberToResponse(Member member) throws Exception {
        return MemberDto.Response.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .address(aesEncryption.doDecrypt(member.getAddress()))
                .nickName(member.getNickName())
                .email(member.getEmail())
                .roles(member.getMemberRole().getRole())
                .password(member.getPassword())
                .phone(aesEncryption.doDecrypt(member.getPhone()))
                .profileImg(member.getProfileImg())
                .build();
    }
}
